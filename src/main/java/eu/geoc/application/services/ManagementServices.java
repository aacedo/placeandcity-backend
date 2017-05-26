package eu.geoc.application.services;

import com.google.gson.Gson;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.FinalComments;
import eu.geoc.application.model.FirstData;
import eu.geoc.application.model.LastData;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.model.UserDetails;
import eu.geoc.application.persistence.FPGsonBuilder;
import eu.geoc.application.persistence.MongoDatabaseManager;
import eu.geoc.application.persistence.PersistenceBuilder;
import eu.geoc.application.services.model.IdResult;
import eu.geoc.application.services.model.LayersResult;
import eu.geoc.application.util.GeoJsonOperations;
import org.bson.types.ObjectId;
import org.geojson.FeatureCollection;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

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
		Gson gson = FPGsonBuilder.getNewGson();
		String json = gson.toJson(docs);
		slotDB.disconnect();
		return json;
	}

	@POST
	@Path("lisbon_citizen")
	public IdResult setHome(FirstData data) {
		slotDB.connect();
		data.setDate(new DateTime().toString());
		Gson gson = FPGsonBuilder.getNewGson();
		String json = gson.toJson(data);
		ObjectId id = slotDB.insert(json);
		slotDB.disconnect();
		return new IdResult(id.toString());
	}

	@GET
	@Path("SOP_data")
	public LayersResult getAllSOP() {
		try {
			slotDB.connect();
			List<FeatureCollection> allSopLayers = slotDB.getAllSOPLayers();
			slotDB.disconnect();
			FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(allSopLayers);
			return new LayersResult("0", joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", null);
		}
	}

	@GET
	@Path("SOP_data/{id}")
	public LayersResult getSOPs(@PathParam("id") String id) {
		try {
			slotDB.connect();
			List<FeatureCollection> sopLayers = slotDB.getSOPLayersFromSurvey(id);
			slotDB.disconnect();
			FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(sopLayers);
			return new LayersResult(id, joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", null);
		}
	}

	@POST
	@Path("SOP_data")
	public IdResult setSOP(SOPAreasList data) {
		try {
			slotDB.connect();
			slotDB.addSOPData(data);
			slotDB.disconnect();
			return new IdResult(data.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@GET
	@Path("SC_data")
	public LayersResult getAllSC() {
		try {
			slotDB.connect();
			List<FeatureCollection> allScLayers = slotDB.getAllSCLayers();
			slotDB.disconnect();
			FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(allScLayers);
			return new LayersResult("0", joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", null);
		}
	}

	@GET
	@Path("SC_data/{id}")
	public LayersResult getSC(@PathParam("id") String id) {
		try {
			slotDB.connect();
			List<FeatureCollection> scLayers = slotDB.getSCLayersFromSurvey(id);
			slotDB.disconnect();
			FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(scLayers);
			return new LayersResult(id, joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", null);
		}
	}

	@POST
	@Path("SC_data")
	public IdResult setSC(SCAreasList data) {
		try {
			slotDB.connect();
			slotDB.addSCData(data);
			slotDB.disconnect();
			return new IdResult(data.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@GET
	@Path("CE_data")
	public LayersResult getAllCE() {
		try {
			slotDB.connect();
			List<FeatureCollection> allCeLayers = slotDB.getAllCELayers();
			slotDB.disconnect();
			FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(allCeLayers);
			return new LayersResult("0", joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", null);
		}
	}

	@GET
	@Path("CE_data/{id}")
	public LayersResult getCE(@PathParam("id") String id) {
		try {
			slotDB.connect();
			List<FeatureCollection> ceLayers = slotDB.getCELayersFromSurvey(id);
			slotDB.disconnect();
			FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(ceLayers);
			return new LayersResult(id, joinGeoJson);
		}
		catch (Exception e){
			e.printStackTrace();
			return new LayersResult("0", null);
		}
	}

	@POST
	@Path("CE_data")
	public IdResult setCE(CEAreasList data) {
		try {
			slotDB.connect();
			slotDB.addCEData(data);
			slotDB.disconnect();
			return new IdResult(data.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@POST
	@Path("finish")
	public IdResult finish(UserDetails userDetails) {
		try {
			slotDB.connect();
			slotDB.addUserDetails(userDetails);
			slotDB.disconnect();
			return new IdResult(userDetails.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@POST
	@Path("final_details")
	public IdResult setFinalDetails(LastData finalDetails) {
		slotDB.connect();
		slotDB.addFinalDetails(finalDetails);
		slotDB.disconnect();
		return new IdResult(finalDetails.getId());
	}

	@POST
	@Path("comments")
	public IdResult setFinalComments(FinalComments finalComments) {
		slotDB.connect();
		slotDB.addFinalDetails(finalComments);
		slotDB.disconnect();
		return new IdResult(finalComments.getId());
	}

	@GET
	@Path("all")
	@Produces(TEXT_PLAIN)
	public String getItAll() {
		slotDB.connect();
		List<String> all = slotDB.getDocs();
		String str ="[";
		for (String s : all) {
			str = str + s + ",";
		}
		str = str.substring(0, str.length()-1);
		str = str + "]";
		str = str.replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}");
		slotDB.disconnect();
		return str;
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
	public IdResult getAllSOP(String _id) {
		try {
			List<String> sopLayers = slotDB.getLayersFromSurvey(_id);
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

