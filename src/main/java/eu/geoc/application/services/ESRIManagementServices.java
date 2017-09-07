package eu.geoc.application.services;

import eu.geoc.application.persistence.PersistenceBuilder;
import eu.geoc.application.services.model.MongoDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes({APPLICATION_JSON})
@Produces({APPLICATION_JSON})
@Path("esri")
public class ESRIManagementServices extends BaseManagementServices {

	public ESRIManagementServices() {
		super();
		PersistenceBuilder.getInstance().esriInit();
		this.dao = new MongoDAO(PersistenceBuilder.getInstance().getMongoDatabaseManager());
	}
}

