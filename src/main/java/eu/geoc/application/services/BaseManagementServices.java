package eu.geoc.application.services;

import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.services.model.*;
import org.geojson.FeatureCollection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 05/07/2017.
 */

public class BaseManagementServices {
    protected MongoDAO dao;

    public BaseManagementServices() {
        super();
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
        return new IdResult(dao.setHome(data));
    }

    @GET
    @Path("SOP_data")
    public LayersResult getAllSOP() {
        return dao.getAllSOP();
    }

    @GET
    @Path("SOP_data/{id}")
    public LayersResult getSOPs(@PathParam("id") String id) {
       return dao.getSOPs(id);
    }

    @POST
    @Path("SOP_data/{id}")
    public IdResult setSOP(@PathParam("id") String id, SOPAreasList data) {
        return new IdResult(dao.setSOP(id, data));
    }

    @GET
    @Path("SC_data")
    public LayersResult getAllSC() {
        FeatureCollection features = dao.getAllSC();
        return new LayersResult(MongoDAO.INVALID_ID, features);
    }

    @GET
    @Path("SC_data/{id}")
    public LayersResult getSC(@PathParam("id") String id) {
        FeatureCollection features = dao.getSC(id);
        return new LayersResult(id, features);
    }

    @POST
    @Path("SC_data/{id}")
    public IdResult setSC(@PathParam("id") String id, SCAreasList data) {
        return new IdResult(dao.setSC(id, data));
    }

    @GET
    @Path("CE_data")
    public LayersResult getAllCE() {
        FeatureCollection features = dao.getAllCE();
        return new LayersResult(MongoDAO.INVALID_ID, features);
    }

    @GET
    @Path("CE_data/{id}")
    public LayersResult getCE(@PathParam("id") String id) {
        FeatureCollection features = dao.getCE(id);
        return new LayersResult(id, features);
    }

    @POST
    @Path("CE_data/{id}")
    public IdResult setCE(@PathParam("id") String id, CEAreasList data) {
        return new IdResult(dao.setCE(id, data));
    }

    @POST
    @Path("finish/{id}")
    public IdResult finish(@PathParam("id") String id, UserDetails userDetails) {
        return new IdResult(dao.finish(id, userDetails));
    }

    @POST
    @Path("final_details/{id}")
    public IdResult setFinalDetails(@PathParam("id") String id, LastData finalDetails) {
        return new IdResult(dao.setFinalDetails(id, finalDetails));
    }

    @POST
    @Path("comments/{id}")
    public IdResult setFinalComments(@PathParam("id") String id, FinalComments finalComments) {
        return new IdResult(dao.setFinalComments(id, finalComments));
    }

    @GET
    @Path("all")
    @Produces(TEXT_PLAIN)
    public String getItAll() {
        return dao.getItAll();
    }

    @GET
    @Path("geom")
    public LayersResult getAllGeometries() {
        FeatureCollection features = dao.getAllGeometries();
        return new LayersResult(MongoDAO.INVALID_ID, features);
    }

    @GET
    @Path("usergeoms")
    @Produces("application/zip")
    public Response getByUserGeoms() {
        ByteArrayOutputStream byUserGeoms = dao.getByUserGeoms();
        if (byUserGeoms != null) {
            return Response.ok(byUserGeoms.toByteArray(), "application/zip")
                    .header("Content-Disposition", "attachment; filename=\"" + "geoms.zip" + "\"") //optional
                    .build();
        }
        else{
            return Response.serverError().build();
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
