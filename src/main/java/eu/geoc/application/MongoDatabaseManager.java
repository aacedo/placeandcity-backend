package eu.geoc.application;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;

public class MongoDatabaseManager {
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

	public ObjectId updateRecord(String id, String fieldName, String data){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Document doc = Document.parse(records.get(0));
		doc.put(fieldName, data);
		MongoCollection<Document> collection = this.database.getCollection(mainCollection);
		Document id1 = collection.findOneAndReplace(eq("_id", new ObjectId(id)), doc);
		return doc.getObjectId("_id");
	}

	public SOPList getDocSOPList(String id, String fieldName){
		List<String> records = getRecords(mainCollection, new Document("_id", new ObjectId(id)));
		Document doc = Document.parse(records.get(0));
		Document sopDoc = (Document)(doc.get(fieldName));
		Gson gson = new Gson();
		SOPList list = gson.fromJson(sopDoc.toJson(), SOPList.class);
		return list;
	}

	public List<String> getSOPLayersFromSurvey(String id){
		List<String> sopLayers = new ArrayList<>();
		SOPList sopList = getDocSOPList(id, "SOP");
		for (SOP sop : sopList.getSops()) {
			sopLayers.add(sop.getLayer());
		}
		return sopLayers;
	}
}


