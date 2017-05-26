package eu.geoc.application.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.CE.CEArea;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.AreasList;
import eu.geoc.application.model.SC.SCArea;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPArea;
import eu.geoc.application.model.SOP.SOPAreasList;
import org.geojson.FeatureCollection;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

public class FPGsonBuilder {
    public static Gson getNewGson(){
        RuntimeTypeAdapterFactory<AreasList> adapterALFactory = RuntimeTypeAdapterFactory
                .of(AreasList.class,"type")
                .registerSubtype(SOPAreasList.class, "sop")
                .registerSubtype(SCAreasList.class, "sc")
                .registerSubtype(CEAreasList.class, "ce");
        RuntimeTypeAdapterFactory<BasicArea> adapterAFactory = RuntimeTypeAdapterFactory
                .of(BasicArea.class,"type")
                .registerSubtype(SOPArea.class, "sopa")
                .registerSubtype(SCArea.class, "sca")
                .registerSubtype(CEArea.class, "cea");
        return new GsonBuilder()
                .registerTypeAdapterFactory(adapterALFactory)
                .registerTypeAdapterFactory(adapterAFactory)
                .registerTypeAdapter(FeatureCollection.class, new FeatureCollectionDeSerializer())
                .setPrettyPrinting()
                .create();
    }
}