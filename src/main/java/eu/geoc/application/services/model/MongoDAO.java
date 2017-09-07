package eu.geoc.application.services.model;

import com.google.gson.Gson;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.model.UserEntry;
import eu.geoc.application.persistence.FPGsonBuilder;
import eu.geoc.application.persistence.MongoDatabaseManager;
import eu.geoc.application.util.GeoJsonOperations;
import org.geojson.FeatureCollection;
import org.joda.time.DateTime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 05/07/2017.
 */

public class MongoDAO{
    protected MongoDatabaseManager slotDB;
    public static final String INVALID_ID = "0";

    public MongoDAO(MongoDatabaseManager slotDB) {
        super();
        this.slotDB = slotDB;
    }

    public String setHome(FirstData data){
        return setHome(data, true);
    }

    public String setHome(FirstData data, boolean closeConn) {
        slotDB.connect();
        data.setDate(new DateTime().toString());
        String id = slotDB.insertUserDetails(data);
        if (closeConn) {slotDB.disconnect();}
        return id;
    }

    public LayersResult getAllSOP(){
        return getAllSOP(true);
    }

    public LayersResult getAllSOP(boolean closeConn) {
        try {
            slotDB.connect();
            List<FeatureCollection> allSopLayers = slotDB.getAllSOPLayers();
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(allSopLayers);
            return new LayersResult(INVALID_ID, joinGeoJson);
        }
        catch (Exception e){
            e.printStackTrace();
            return new LayersResult(INVALID_ID, null);
        }
    }

    public LayersResult getSOPs(String id){
        return getSOPs(id, true);
    }

    public LayersResult getSOPs(String id, boolean closeConn) {
        try {
            slotDB.connect();
            List<FeatureCollection> sopLayers = slotDB.getSOPLayersFromSurvey(id);
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(sopLayers);
            return new LayersResult(id, joinGeoJson);
        }
        catch (Exception e){
            e.printStackTrace();
            return new LayersResult(INVALID_ID, null);
        }
    }

    public String setSOP(String id, SOPAreasList data){
        return setSOP(id, data, true);
    }

    public String setSOP(String id, SOPAreasList data, boolean closeConn) {
        try {
            slotDB.connect();
            slotDB.addDetails(id, new SOPFiller(data));
            if (closeConn) {slotDB.disconnect();}
            return id;
        }
        catch (Exception e){
            e.printStackTrace();
            return INVALID_ID;
        }
    }

    public FeatureCollection getAllSC(){
        return getAllSC(true);
    }

    public FeatureCollection getAllSC(boolean closeConn) {
        try {
            slotDB.connect();
            List<FeatureCollection> allScLayers = slotDB.getAllSCLayers();
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(allScLayers);
            return joinGeoJson;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FeatureCollection getSC(String id){
        return getSC(id, true);
    }

    public FeatureCollection getSC(String id, boolean closeConn) {
        try {
            slotDB.connect();
            List<FeatureCollection> scLayers = slotDB.getSCLayersFromSurvey(id);
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(scLayers);
            return joinGeoJson;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String setSC(String id, SCAreasList data){
        return setSC(id, data,true);
    }

    public String setSC(String id, SCAreasList data, boolean closeConn) {
        try {
            slotDB.connect();
            slotDB.addDetails(id, new SCFiller(data));
            if (closeConn) {slotDB.disconnect();}
            return id;
        }
        catch (Exception e){
            e.printStackTrace();
            return INVALID_ID;
        }
    }

    public FeatureCollection getAllCE(){
        return getAllCE(true);
    }

    public FeatureCollection getAllCE(boolean closeConn) {
        try {
            slotDB.connect();
            List<FeatureCollection> allCeLayers = slotDB.getAllCELayers();
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(allCeLayers);
            return joinGeoJson;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FeatureCollection getCE(String id){
        return getCE(id, true);
    }

    public FeatureCollection getCE(String id, boolean closeConn) {
        try {
            slotDB.connect();
            List<FeatureCollection> ceLayers = slotDB.getCELayersFromSurvey(id);
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection joinGeoJson = GeoJsonOperations.joinGeoJson(ceLayers);
            return joinGeoJson;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String setCE(String id, CEAreasList data){
        return setCE(id, data, true);
    }

    public String setCE(String id, CEAreasList data, boolean closeConn) {
        try {
            slotDB.connect();
            slotDB.addDetails(id, new CEFiller(data));
            if (closeConn) {slotDB.disconnect();}
            return id;
        }
        catch (Exception e){
            e.printStackTrace();
            return INVALID_ID;
        }
    }

    public String finish(String id, UserDetails userDetails){
        return finish(id, userDetails,true);
    }

    public String finish(String id, UserDetails userDetails, boolean closeConn) {
        try {
            slotDB.connect();
            slotDB.addDetails(id, userDetails);
            if (closeConn) {slotDB.disconnect();}
            return id;
        }
        catch (Exception e){
            e.printStackTrace();
            return INVALID_ID;
        }
    }

    public String setFinalDetails(String id, LastData finalDetails){
        return setFinalDetails(id, finalDetails, true);
    }

    public String setFinalDetails(String id, LastData finalDetails, boolean closeConn) {
        slotDB.connect();
        slotDB.addDetails(id, finalDetails);
        if (closeConn) {slotDB.disconnect();}
        return id;
    }

    public String setFinalComments(String id, FinalComments finalComments){
        return setFinalComments(id, finalComments, true);
    }

    public String setFinalComments(String id, FinalComments finalComments, boolean closeConn) {
        slotDB.connect();
        slotDB.addDetails(id, finalComments);
        if (closeConn) {slotDB.disconnect();}
        return id;
    }

    public String getItAll(){
        return getItAll(true);
    }

    public String getItAll(boolean closeConn) {
        slotDB.connect();
        String json = FPGsonBuilder.getNewGson().toJson(slotDB.getEntries());
        if (closeConn) {slotDB.disconnect();}
        return json;
    }

    public FeatureCollection getAllGeometries(){
        return getAllGeometries(true);
    }

    public FeatureCollection getAllGeometries(boolean closeConn) {
        try {
            slotDB.connect();
            List<UserEntry> entries = slotDB.getEntries();
            if (closeConn) {slotDB.disconnect();}
            FeatureCollection geoJsonJoinEntries = GeoJsonOperations.getGeoJsonJoinEntries(entries);
            return geoJsonJoinEntries;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ByteArrayOutputStream getByUserGeoms(){
        return getByUserGeoms(true);
    }

    public ByteArrayOutputStream getByUserGeoms(boolean closeConn) {
        try {
            slotDB.connect();
            List<UserEntry> entries = slotDB.getEntries();
            if (closeConn) {slotDB.disconnect();}
            HashMap<String, FeatureCollection> usersGeoJsons = GeoJsonOperations.getGeoJsonJoinEntriesPerID(entries);
            Gson gson = FPGsonBuilder.getNewGson();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try(ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream)) {
                for (String id : usersGeoJsons.keySet()) {
                    zos.putNextEntry(new ZipEntry(id + ".txt"));
                    String json = gson.toJson(usersGeoJsons.get(id));
                    zos.write(json.getBytes());
                    zos.closeEntry();
                }
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }

            return byteArrayOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close(){
        slotDB.disconnect();
    }
}
