package eu.geoc.application.persistence;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import eu.geoc.application.model.UserEntry;
import eu.geoc.application.services.model.FinalComments;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.AreasList;
import eu.geoc.application.services.model.LastData;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.services.model.UserDetails;
import eu.geoc.application.services.model.UserEntryFiller;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.geojson.FeatureCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static eu.geoc.application.persistence.FPGsonBuilder.*;

import static com.mongodb.client.model.Filters.eq;

public class MongoDatabaseManager {

	private final static String SOPFieldName = "SOP";
	private final static String SCFieldName = "SC";
	private final static String CEFieldName = "CE";

	private MongoDatabase database;
	private MongoClient mongoClient;
	private boolean connected = false;

	public static class MongoDBManagerConfig {
		public String server;
		public Integer port;
		public String dbName;
		public String mainCollection;

		public MongoDBManagerConfig(String server, Integer port, String dbName, String mainCollection) {
			this.server = server;
			this.port = port;
			this.dbName = dbName;
			this.mainCollection = mainCollection;
		}
	}

	private String server;
	private Integer port;
	private String dbName;
	private String mainCollection;

	MongoDatabaseManager(MongoDBManagerConfig configs) {
		super();
		this.server = configs.server;
		this.port = configs.port;
		this.dbName = configs.dbName;
		this.mainCollection = configs.mainCollection;
	}

	//region Connection

	public synchronized boolean connect(){
		if (!connected) {
			try {
				this.mongoClient = new MongoClient(server, port);
				this.database = this.mongoClient.getDatabase(dbName);
				this.connected = true;
			} catch (Exception e) {
				this.mongoClient = null;
				this.connected = false;
			}
		}
		return connected;
	}

	public synchronized void disconnect(){
		if(connected) {
			this.mongoClient.close();
			this.connected = false;
		}
	}

	public boolean isConnected(){
		return this.connected;
	}

	//endregion

	private List<String> getRecords(String collectionName, Document filter){
		List<String> info = new Vector<String>();

		MongoCollection<Document> collection = this.database.getCollection(collectionName);
		MongoCursor<Document> cursor = (filter == null)?
				collection.find()/*.projection(Projections.excludeId())*/.iterator():
				collection.find(filter)/*.projection(Projections.excludeId())*/.iterator();

		while(cursor.hasNext()){
			Document doc = cursor.next();
			String json = doc.toJson();
			info.add(json);
		}
		return info;
	}

	private List<Document> getSimpleRecords(String collectionName){
		List<Document> docs = new Vector<Document>();

		MongoCollection<Document> collection = this.database.getCollection(collectionName);
		MongoCursor<Document> cursor = collection.find()/*.projection(Projections.excludeId())*/.iterator();

		while(cursor.hasNext()){
			Document doc = cursor.next();
			docs.add(doc);
		}
		return docs;
	}

	private ObjectId insertRecord(String collectionName, String record){
		Document doc = Document.parse(record);
		MongoCollection<Document> collection = this.database.getCollection(collectionName);
		collection.insertOne(doc);
		ObjectId id = doc.getObjectId("_id");
		return id;
	}

	public List<UserEntry> getEntries(){
		List<Document> records = getSimpleRecords(mainCollection);
		List<UserEntry> entries = new ArrayList<>();
		Gson gson = getNewGson();
		for (Document record : records) {
			UserEntry userEntry = gson.fromJson(record.toJson(), UserEntry.class);
			ObjectId id = record.getObjectId("_id");
			userEntry.setId(id.toString());
			entries.add(userEntry);
		}
		return entries;
	}

	private String getDocField(String id, String fieldName){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Document doc = Document.parse(records.get(0));
		//Document sopDoc = Document.parse(doc.get(fieldName).toString());
		return ((Document)doc.get(fieldName)).toJson();
	}

	private List<FeatureCollection> getLayersFromSurvey(String id, String fieldName){
		List<FeatureCollection> areaLayers = new ArrayList<>();
		Gson gson = getNewGson();
		String areaListString = getDocField(id, fieldName);
		AreasList areasList = gson.fromJson(areaListString, AreasList.class);
		for (BasicArea area : areasList.getAreas()) {
			areaLayers.add(area.getLayer());
		}
		return areaLayers;
	}

	private List<FeatureCollection> getAllLayers(String fieldName){
		List<FeatureCollection> areaLayers = new ArrayList<>();
		List<Document> records = getSimpleRecords(mainCollection);
		Gson gson = getNewGson();
		for (Document record : records) {
			Object rec = record.get(fieldName);
			if(rec != null) {
				String areaListString = ((Document) rec).toJson();
				AreasList areasList = gson.fromJson(areaListString, AreasList.class);
				if (areasList != null) {
					for (BasicArea area : areasList.getAreas()) {
						areaLayers.add(area.getLayer());
					}
				}
			}
		}
		return areaLayers;
	}

	public List<FeatureCollection> getSOPLayersFromSurvey(String id) {
		return getLayersFromSurvey(id, SOPFieldName);
	}

	public List<FeatureCollection> getSCLayersFromSurvey(String id) {
		return getLayersFromSurvey(id, SCFieldName);
	}

	public List<FeatureCollection> getCELayersFromSurvey(String id) {
		return getLayersFromSurvey(id, CEFieldName);
	}

	public List<FeatureCollection> getAllSOPLayers() {
		return getAllLayers(SOPFieldName);
	}

	public List<FeatureCollection> getAllSCLayers() {
		return getAllLayers(SCFieldName);
	}

	public List<FeatureCollection> getAllCELayers() {
		return getAllLayers(CEFieldName);
	}

	public void addDetails(String id, UserEntryFiller filler){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Gson gson = getNewGson();
		UserEntry userEntry = gson.fromJson(records.get(0), UserEntry.class);
		userEntry.setId(id);
		filler.fill(userEntry);
		userEntry.setId(null);
		Document doc = Document.parse(gson.toJson(userEntry));
		MongoCollection<Document> collection = this.database.getCollection(mainCollection);
		collection.findOneAndReplace(eq("_id", new ObjectId(id)), doc);
	}

	public String insertUserDetails(UserEntryFiller filler){
		UserEntry userEntry = new UserEntry();
		filler.fill(userEntry);
		ObjectId id = insertRecord(mainCollection, getNewGson().toJson(userEntry));
		return id.toString();
	}
}
