package eu.geoc.application;

import com.google.gson.Gson;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes({APPLICATION_JSON})
@Produces({APPLICATION_JSON})
@Path("home")
public class ManagementServices {

	private MongoDatabaseManager slotDB;

	public ManagementServices() {
		super();
		PersistenceBuilder.getInstance().defInit();
		slotDB = PersistenceBuilder.getInstance().getMongoDatabaseManager();
	}

	public Long getTimeStamp(){
		return (System.currentTimeMillis() / 1000L)-1;
	}

	@GET
	public String getSurveys() {
		slotDB.connect();
		List<String> docs = slotDB.getDocs();
		Gson gson = new Gson();
		String json = gson.toJson(docs);
		slotDB.disconnect();
		return json;
	}

	@POST
	@Path("lisbon_citizen")
	public IdResult setHome(FirstData data) {
		slotDB.connect();
		Gson gson = new Gson();
		String json = gson.toJson(data);
		ObjectId id = slotDB.insert(json);
		slotDB.disconnect();
		return new IdResult(id.toString());
	}

	@POST
	@Path("SOP_data")
	public IdResult setSOP(SOPList data) {
		slotDB.connect();
		Gson gson = new Gson();
		String json = gson.toJson(data);
		ObjectId id = slotDB.updateRecord(data.getId(),"SOP",json);
		slotDB.disconnect();
		return new IdResult(data.getId());
	}

	@POST
	@Path("geojson")
	public IdResult parseGeoJson(TesClas data) {
		try {
			List<String> list = wrapInList(data.geoJson);
			String union = GeoJsonOperations.applyOp(list, GeoJsonOperations.Operations.UNION);
			return new IdResult(union);
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@POST
	@Path("joingeojson")
	public IdResult joinGeoJson(TesClas data) {
		try {
			List<String> list = wrapInList(data.geoJson);
			String joinGeoJson = GeoJsonOperations.joinGeoJson(list);
			return new IdResult(joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@POST
	@Path("getgeojson")
	public IdResult getAllSOP(String id) {
		try {
			List<String> sopLayers = slotDB.getSOPLayersFromSurvey(id);
			return new IdResult("1");
		}
		catch (Exception e){
			return new IdResult("0");
		}
	}

	private List<String> wrapInList(String geoJson) {
		List<String> list = new ArrayList<>();
		list.add(geoJson);
		return list;
	}
}

