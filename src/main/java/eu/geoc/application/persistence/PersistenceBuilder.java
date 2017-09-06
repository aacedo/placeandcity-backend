package eu.geoc.application.persistence;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/09/2016.
 */

public class PersistenceBuilder {
    private static PersistenceBuilder instance = null;

    public synchronized static PersistenceBuilder getInstance() {
        if (instance == null) {
            instance = new PersistenceBuilder();
        }
        return instance;
    }

    private MongoDatabaseManager mongoDatabaseManager;

    private PersistenceBuilder() {
    }

    public void mainInit() {
        MongoDatabaseManager.MongoDBManagerConfig configsMGDB = new MongoDatabaseManager.MongoDBManagerConfig(
                "127.0.0.1", 27017, "surveys", "mainCollection");
        mongoDatabaseManager = new MongoDatabaseManager(configsMGDB);
    }

    public void postInit() {
        MongoDatabaseManager.MongoDBManagerConfig configsMGDB = new MongoDatabaseManager.MongoDBManagerConfig(
                "127.0.0.1", 27017, "surveys", "postCollection");
        mongoDatabaseManager = new MongoDatabaseManager(configsMGDB);
    }

    public void esriInit() {
        MongoDatabaseManager.MongoDBManagerConfig configsMGDB = new MongoDatabaseManager.MongoDBManagerConfig(
                "127.0.0.1", 27017, "surveys", "esriCollection");
        mongoDatabaseManager = new MongoDatabaseManager(configsMGDB);
    }

    public MongoDatabaseManager getMongoDatabaseManager() {
        return mongoDatabaseManager;
    }
}
