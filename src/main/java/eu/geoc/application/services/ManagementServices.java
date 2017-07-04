package eu.geoc.application.services;

import com.google.gson.Gson;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.model.UserEntry;
import eu.geoc.application.persistence.FPGsonBuilder;
import eu.geoc.application.persistence.MongoDatabaseManager;
import eu.geoc.application.persistence.PersistenceBuilder;
import eu.geoc.application.services.model.*;
import eu.geoc.application.util.GeoJsonOperations;
import org.geojson.FeatureCollection;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Consumes({APPLICATION_JSON})
@Produces({APPLICATION_JSON})
@Path("home")
public class ManagementServices {

	private MongoDatabaseManager slotDB;

	public ManagementServices() {
		super();
		PersistenceBuilder.getInstance().mainInit();
		slotDB = PersistenceBuilder.getInstance().getMongoDatabaseManager();
	}

	public Long getTimeStamp(){
		return (System.currentTimeMillis() / 1000L)-1;
	}

	@GET
	public String getHomeString() {
		return "home";
	}

	@POST
	@Path("lisbon_citizen")
	public IdResult setHome(FirstData data) {
		slotDB.connect();
		data.setDate(new DateTime().toString());
		String id = slotDB.insertUserDetails(data);
		slotDB.disconnect();
		return new IdResult(id);
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
	@Path("SOP_data/{id}")
	public IdResult setSOP(@PathParam("id") String id, SOPAreasList data) {
		try {
			slotDB.connect();
			slotDB.addDetails(id, new SOPFiller(data));
			slotDB.disconnect();
			return new IdResult(id);
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
	@Path("SC_data/{id}")
	public IdResult setSC(@PathParam("id") String id, SCAreasList data) {
		try {
			slotDB.connect();
			slotDB.addDetails(id, new SCFiller(data));
			slotDB.disconnect();
			return new IdResult(id);
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
	@Path("CE_data/{id}")
	public IdResult setCE(@PathParam("id") String id, CEAreasList data) {
		try {
			slotDB.connect();
			slotDB.addDetails(id, new CEFiller(data));
			slotDB.disconnect();
			return new IdResult(id);
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@POST
	@Path("finish/{id}")
	public IdResult finish(@PathParam("id") String id, UserDetails userDetails) {
		try {
			slotDB.connect();
			slotDB.addDetails(id, userDetails);
			slotDB.disconnect();
			return new IdResult(id);
		}
		catch (Exception e){
			e.printStackTrace();
			return new IdResult("0");
		}
	}

	@POST
	@Path("final_details/{id}")
	public IdResult setFinalDetails(@PathParam("id") String id, LastData finalDetails) {
		slotDB.connect();
		slotDB.addDetails(id, finalDetails);
		slotDB.disconnect();
		return new IdResult(id);
	}

	@POST
	@Path("comments/{id}")
	public IdResult setFinalComments(@PathParam("id") String id, FinalComments finalComments) {
		slotDB.connect();
		slotDB.addDetails(id, finalComments);
		slotDB.disconnect();
		return new IdResult(id);
	}

	@GET
	@Path("all")
	@Produces(TEXT_PLAIN)
	public String getItAll() {
		slotDB.connect();
		String json = FPGsonBuilder.getNewGson().toJson(slotDB.getEntries());
		slotDB.disconnect();
		return json;
	}

	@GET
	@Path("geom")
	public LayersResult getAllGeometries() {
		try {
			slotDB.connect();
			List<UserEntry> entries = slotDB.getEntries();
			FeatureCollection geoJsonJoinEntries = GeoJsonOperations.getGeoJsonJoinEntries(entries);
			return new LayersResult("0", geoJsonJoinEntries);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			slotDB.disconnect();
		}
	}

	@GET
	@Path("usergeoms")
	@Produces("application/zip")
	public Response getByUserGeoms() {
		try {
			slotDB.connect();
			List<UserEntry> entries = slotDB.getEntries();
			HashMap<String, FeatureCollection> usersGeoJsons = GeoJsonOperations.getGeoJsonJoinEntriesPerID(entries);
			Gson gson = FPGsonBuilder.getNewGson();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			try(ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream)) {
				for (String id : usersGeoJsons.keySet()) {
					zos.putNextEntry(new ZipEntry(id + ".txt"));
					String json = gson.toJson(usersGeoJsons.get(id));
					zos.write(json.getBytes());
					zos.closeEntry();
				}
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}

			return Response.ok(byteArrayOutputStream.toByteArray(), "application/zip")
					.header("Content-Disposition", "attachment; filename=\"" + "geoms.zip" + "\"" ) //optional
					.build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		finally {
			slotDB.disconnect();
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

