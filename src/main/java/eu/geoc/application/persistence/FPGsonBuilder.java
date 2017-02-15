package eu.geoc.application.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.AreasList;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

public class FPGsonBuilder {
    public static Gson getNewGson(){
        RuntimeTypeAdapterFactory<AreasList> adapterFactory = RuntimeTypeAdapterFactory
                .of(AreasList.class,"type")
                .registerSubtype(SOPAreasList.class, "sop")
                .registerSubtype(SCAreasList.class, "sc")
                .registerSubtype(CEAreasList.class, "ce");
        return new GsonBuilder().registerTypeAdapterFactory(adapterFactory).setPrettyPrinting().create();
    }
}