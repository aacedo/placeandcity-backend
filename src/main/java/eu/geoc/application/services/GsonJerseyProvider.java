package eu.geoc.application.services;

import com.google.gson.Gson;
import eu.geoc.application.persistence.FPGsonBuilder;
import org.geojson.FeatureCollection;

import java.io.*;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/05/2017.
 */

public class GsonJerseyProvider<T> {
    protected static final String UTF_8 = "UTF-8";
    protected Gson gson = FPGsonBuilder.getNewGson();

    protected void toStream(T object, Class<?> type, OutputStream entityStream) throws IOException {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(entityStream, UTF_8);
            gson.toJson(object, type, writer);
            writer.flush();
        }catch (Exception e){
            if (writer != null){
                writer.close();
            }
        } /*} finally {
            writer.close();
        }*/
    }

    protected T fromStream(Class<T> type, InputStream entityStream) throws IOException {
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(entityStream, UTF_8);
            return gson.fromJson(streamReader, type);
        } catch (com.google.gson.JsonSyntaxException e) {
            if (streamReader != null){
                streamReader.close();
            }
            return null;
        } /*finally {
            streamReader.();
        }*/
    }
}
