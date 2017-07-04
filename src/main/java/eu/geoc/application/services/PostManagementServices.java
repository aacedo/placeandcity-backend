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
@Path("post")
public class PostManagementServices {

	private MongoDatabaseManager slotDB;

	public PostManagementServices() {
		super();
		PersistenceBuilder.getInstance().postInit();
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
	public String pong(LayersResult layerResult) {
		return "OK";
	}
}

