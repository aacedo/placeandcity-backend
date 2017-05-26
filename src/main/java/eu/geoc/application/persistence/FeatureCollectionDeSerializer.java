package eu.geoc.application.persistence;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;

import java.lang.reflect.Type;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 25/05/2017.
 */

public class FeatureCollectionDeSerializer implements JsonDeserializer<FeatureCollection>, JsonSerializer<FeatureCollection> {

    @Override
    public FeatureCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try{
            String json = jsonElement.getAsJsonObject().toString();
            GeoJsonObject geoJsonObject = new ObjectMapper().registerModule(new JtsModule()).readValue(json, GeoJsonObject.class);
            if (geoJsonObject instanceof FeatureCollection) {
                return (FeatureCollection) geoJsonObject;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonElement serialize(FeatureCollection fc, Type type, JsonSerializationContext jsonSerializationContext) {
        try{
            return new JsonParser().parse(new ObjectMapper().registerModule(new JtsModule()).writeValueAsString(fc));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
