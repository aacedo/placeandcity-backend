package eu.geoc.application.persistence;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.AreasList;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.model.UserDetails;
import org.bson.Document;
import org.bson.types.ObjectId;

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

	private List<String> getRecords(String collectionName){
		return getRecords(collectionName, null);
	}

	private List<String> getRecords(String collectionName, Document filter){
		List<String> info = new Vector<String>();

		MongoCollection<Document> collection = this.database.getCollection(collectionName);
		MongoCursor<Document> cursor = (filter == null)?
				collection.find().projection(Projections.excludeId()).iterator():
				collection.find(filter).projection(Projections.excludeId()).iterator();

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
		MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

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

	public ObjectId insert(String json){
		return insertRecord(mainCollection, json);
	}

	public List<String>  getDocs(){
		return getRecords(mainCollection);
	}

	public void updateRecord(String id, String fieldName, String data){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Document doc = Document.parse(records.get(0));
		Document docData = Document.parse(data);
		doc.put(fieldName, docData);
		MongoCollection<Document> collection = this.database.getCollection(mainCollection);
		Document id1 = collection.findOneAndReplace(eq("_id", new ObjectId(id)), doc);
	}

	public void mergeAndUpdateRecord(String id, String data){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Document doc = Document.parse(records.get(0));
		Document parsed = Document.parse(data);
		doc.putAll(parsed);
		MongoCollection<Document> collection = this.database.getCollection(mainCollection);
		Document id1 = collection.findOneAndReplace(eq("_id", new ObjectId(id)), doc);
	}

	public String getDocField(String id, String fieldName){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Document doc = Document.parse(records.get(0));
		//Document sopDoc = Document.parse(doc.get(fieldName).toString());
		return ((Document)doc.get(fieldName)).toJson();
	}

	public  List<String> getLayersFromSurvey(String id, String fieldName){
		List<String> areaLayers = new ArrayList<>();
		Gson gson = getNewGson();
		String areaListString = getDocField(id, fieldName);
		AreasList areasList = gson.fromJson(areaListString, AreasList.class);
		for (BasicArea area : areasList.getAreas()) {
			areaLayers.add(area.getLayer());
		}
		return areaLayers;
	}

	public  List<String> getAllLayers(String fieldName){
		List<String> areaLayers = new ArrayList<>();
		List<Document> records = getSimpleRecords(mainCollection);
		Gson gson = getNewGson();
		for (Document record : records) {
			String areaListString = ((Document)record.get(fieldName)).toJson();
			AreasList areasList = gson.fromJson(areaListString, AreasList.class);
			for (BasicArea area : areasList.getAreas()) {
				areaLayers.add(area.getLayer());
			}
		}
		return areaLayers;
	}

	public void addSOPData(SOPAreasList data){
		String json = getJsonString(data);
		updateRecord(data.getId(), SOPFieldName, json);
	}

	public void addSCData(SCAreasList data){
		String json = getJsonString(data);
		updateRecord(data.getId(), SCFieldName, json);
	}

	public void addCEData(CEAreasList data){
		String json = getJsonString(data);
		updateRecord(data.getId(), CEFieldName, json);
	}

	private String getJsonString(AreasList data) {
		String id = data.getId();
		data.setId(null);
		String json = getNewGson().toJson(data, AreasList.class);
		data.setId(id);
		return json;
	}

	public  List<String> getSOPLayersFromSurvey(String id) {
		return getLayersFromSurvey(id, SOPFieldName);
	}

	public  List<String> getSCLayersFromSurvey(String id) {
		return getLayersFromSurvey(id, SCFieldName);
	}

	public  List<String> getCELayersFromSurvey(String id) {
		return getLayersFromSurvey(id, CEFieldName);
	}

	public  List<String> getAllSOPLayers() {
		return getAllLayers(SOPFieldName);
	}

	public  List<String> getAllSCLayers() {
		return getAllLayers(SCFieldName);
	}

	public  List<String> getAllCELayers() {
		return getAllLayers(CEFieldName);
	}

	public void addUserDetails(UserDetails userDetails){
		String id = userDetails.getId();
		userDetails.setId(null);
		String json = getNewGson().toJson(userDetails);
		userDetails.setId(id);
		mergeAndUpdateRecord(userDetails.getId(), json);
	}
}
