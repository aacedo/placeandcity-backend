package eu.geoc.application.services;

import com.google.gson.Gson;
import eu.geoc.application.model.FactorList;
import eu.geoc.application.model.FirstData;
import eu.geoc.application.persistence.MongoDatabaseManager;
import eu.geoc.application.persistence.PersistenceBuilder;
import eu.geoc.application.services.model.IdResult;
import eu.geoc.application.services.model.LayersResult;
import eu.geoc.application.util.GeoJsonOperations;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import java.util.List;

import static eu.geoc.application.persistence.FPGsonBuilder.getNewGson;
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

	@GET
	@Path("SOP_data/{id}")
	public LayersResult getSOPs(@PathParam("id") String id) {
		try {
			slotDB.connect();
			List<String> sopLayers = slotDB.getSOPLayersFromSurvey(id);
			slotDB.disconnect();
			String joinGeoJson = GeoJsonOperations.joinGeoJson(sopLayers);
			return new LayersResult(id, joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", "");
		}
	}

	@POST
	@Path("SOP_data")
	public IdResult setSOP(String factorListJson) {
		try {
			slotDB.connect();
			FactorList data = getNewGson().fromJson(factorListJson, FactorList.class);		// Verification
			ObjectId id = slotDB.addSOPData(data);
			slotDB.disconnect();
			return new IdResult(data.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@GET
	@Path("SC_data/{id}")
	public LayersResult getSC(@PathParam("id") String id) {
		try {
			slotDB.connect();
			List<String> sopLayers = slotDB.getSCLayersFromSurvey(id);
			slotDB.disconnect();
			String joinGeoJson = GeoJsonOperations.joinGeoJson(sopLayers);
			return new LayersResult(id, joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", "");
		}
	}

	@POST
	@Path("SC_data")
	public IdResult setSC(String factorListJson) {
		try {
			slotDB.connect();
			FactorList data = getNewGson().fromJson(factorListJson, FactorList.class);		// Verification
			ObjectId id = slotDB.addSCData(data);
			slotDB.disconnect();
			return new IdResult(data.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@GET
	@Path("CE_data/{id}")
	public LayersResult getCE(@PathParam("id") String id) {
		try {
			slotDB.connect();
			List<String> sopLayers = slotDB.getCELayersFromSurvey(id);
			slotDB.disconnect();
			String joinGeoJson = GeoJsonOperations.joinGeoJson(sopLayers);
			return new LayersResult(id, joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", "");
		}
	}

	@POST
	@Path("CE_data")
	public IdResult setCE(String factorListJson) {
		try {
			slotDB.connect();
			FactorList data = getNewGson().fromJson(factorListJson, FactorList.class);		// Verification
			ObjectId id = slotDB.addCEData(data);
			slotDB.disconnect();
			return new IdResult(data.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}


	//region Commented to be used as example
	/*@POST
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
			List<String> sopLayers = slotDB.getLayersFromSurvey(id);
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
	}*/

	//endregion
}

