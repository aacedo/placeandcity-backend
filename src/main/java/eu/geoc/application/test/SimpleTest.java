package eu.geoc.application.test;

import com.google.gson.Gson;
import eu.geoc.application.persistence.FPGsonBuilder;
import eu.geoc.application.services.GsonALJerseyProvider;
import eu.geoc.application.services.GsonResultJerseyProvider;
import eu.geoc.application.services.model.LayersResult;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 03/07/2017.
 */

public class SimpleTest {
    public static void main(String[] args) {
        resendAll();
        //resendGeometries();
    }

    private static void resendAll() {
        Client oldClient = ClientBuilder.newClient().register(GsonResultJerseyProvider.class).register(GsonALJerseyProvider.class);
        WebTarget oldTarget = oldClient.target("http://localhost:8080").path("home/all");

        String all = oldTarget.request(MediaType.TEXT_PLAIN).get(String.class);

        Client newClient = ClientBuilder.newClient().register(GsonResultJerseyProvider.class).register(GsonALJerseyProvider.class);
        Logger logger = Logger.getLogger(SimpleTest.class.getName());
        Feature feature = new LoggingFeature(logger, Level.SEVERE, LoggingFeature.Verbosity.PAYLOAD_ANY, null);
        newClient.register(feature);
        WebTarget newTarget = newClient.target("http://localhost:8080").path("post/all");

        Gson gson = FPGsonBuilder.getNewGson();
        String result = newTarget.request(MediaType.TEXT_PLAIN)
                .post(Entity.text(all), String.class);
        System.out.println(result);
    }

    private static void resendGeometries() {
        Client oldClient = ClientBuilder.newClient().register(GsonResultJerseyProvider.class).register(GsonALJerseyProvider.class);
        WebTarget oldTarget = oldClient.target("http://localhost:8080").path("home/geom");


        LayersResult layerResult = oldTarget.request(MediaType.APPLICATION_JSON)
                .get(LayersResult.class);


        Client newClient = ClientBuilder.newClient().register(GsonResultJerseyProvider.class).register(GsonALJerseyProvider.class);
        Logger logger = Logger.getLogger(SimpleTest.class.getName());
        Feature feature = new LoggingFeature(logger, Level.SEVERE, LoggingFeature.Verbosity.PAYLOAD_ANY, null);
        newClient.register(feature);
        WebTarget newTarget = newClient.target("http://localhost:8080").path("post");

        Gson gson = FPGsonBuilder.getNewGson();
        String result = newTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(gson.toJson(layerResult)), String.class);
        System.out.println(result);
    }
}
