package eu.geoc.application.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import eu.geoc.application.model.CE;
import eu.geoc.application.model.Factor;
import eu.geoc.application.model.SC;
import eu.geoc.application.model.SOP;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

public class FPGsonBuilder {
    public static Gson getNewGson(){
        RuntimeTypeAdapterFactory<Factor> adapterFactory = RuntimeTypeAdapterFactory
                .of(Factor.class,"type")
                .registerSubtype(SOP.class, "sop")
                .registerSubtype(SC.class, "sc")
                .registerSubtype(CE.class, "ce");
        return new GsonBuilder().registerTypeAdapterFactory(adapterFactory).setPrettyPrinting().create();
    }
}