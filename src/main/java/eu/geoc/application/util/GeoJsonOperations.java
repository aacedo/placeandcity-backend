package eu.geoc.application.util;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Polygon;
import eu.geoc.application.model.AreasList;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.SC.SCArea;
import eu.geoc.application.model.SC.SCAreasGroup;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.model.UserEntry;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 02/02/2017.
 */

public class GeoJsonOperations {
    public enum Operations{
        UNION, INTERSECTION, DIFFERENCE
    }


    public static FeatureCollection getGeoJsonJoinEntries(List<UserEntry> userEntries) throws IOException {
        List<FeatureCollection> geoJsonList = new ArrayList<>();
        for (UserEntry userEntry : userEntries) {
            SOPAreasList sop = (SOPAreasList) userEntry.getSOP();
            for (BasicArea basicArea : sop.getAreas()) {
                FeatureCollection layer = basicArea.getLayer();
                for (Feature feature : layer) {
                    Map<String, Object> properties = feature.getProperties();
                    putBasicProperties("SOP", userEntry, properties);
                }
                geoJsonList.add(layer);
            }

            SCAreasList sc = (SCAreasList) userEntry.getSC();
            for (SCAreasGroup group : sc.getGroups()) {
                for (SCArea scArea : group.getAreas()) {
                    FeatureCollection layer = scArea.getLayer();
                    for (Feature feature : layer) {
                        Map<String, Object> properties = feature.getProperties();
                        putBasicProperties("SC", userEntry, properties);
                        properties.put("bosc1", scArea.getSocialCapital().getBosc1());
                        properties.put("bosc2", scArea.getSocialCapital().getBosc2());
                        properties.put("brsc1", scArea.getSocialCapital().getBrsc1());
                        properties.put("brsc2", scArea.getSocialCapital().getBrsc2());

                    }
                    geoJsonList.add(layer);
                }
            }

            CEAreasList ce = (CEAreasList) userEntry.getCE();
            for (BasicArea basicArea : ce.getAreas()) {
                FeatureCollection layer = basicArea.getLayer();
                for (Feature feature : layer) {
                    Map<String, Object> properties = feature.getProperties();
                    putBasicProperties("CE", userEntry, properties);
                }
                geoJsonList.add(layer);
            }
        }
        return joinGeoJson(geoJsonList);
    }

    private static void putBasicProperties(String typeValue, UserEntry userEntry, Map<String, Object> properties) {
        properties.put("Type", typeValue);
        properties.put("Id", userEntry.getId());
        properties.put("Date", userEntry.getDate());
        properties.put("Email", userEntry.getMailUser());
    }

    public static FeatureCollection joinGeoJson(List<FeatureCollection> geoJsonList) throws IOException {
        FeatureCollection nfc = new FeatureCollection();
        for (FeatureCollection fc : geoJsonList) {
            nfc.addAll(fc.getFeatures());
        }
        return nfc;
    }

    public static FeatureCollection applyOp(List<FeatureCollection> fcList, Operations operation) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        com.vividsolutions.jts.geom.Geometry result = null;

        for (FeatureCollection fc : fcList) {
            List<Feature> features = fc.getFeatures();
            for (Feature feature : features) {
                String geometryJson = mapper.writeValueAsString(feature);
                Polygon polygon = readJTSPolygon(mapper, geometryJson);
                if (result == null){
                    result = polygon;
                }
                switch (operation){
                    case UNION:
                        result = result.union(polygon);
                        break;
                    case INTERSECTION:
                        result = result.intersection(polygon);
                    case DIFFERENCE:
                        result = result.difference(polygon);
                }

            }
        }
        FeatureCollection nfc = createFeatureCollection(mapper, result);
        return nfc;
    }

    private static FeatureCollection createFeatureCollection(ObjectMapper mapper, com.vividsolutions.jts.geom.Geometry result) throws IOException {
        String polyGeoJson = mapper.writeValueAsString(result);
        Reader reader = new StringReader(polyGeoJson);
        GeoJsonObject geometry = new ObjectMapper().readValue(reader, Geometry.class);
        Feature feature = new Feature();
        feature.setGeometry(geometry);
        FeatureCollection fc = new FeatureCollection();
        fc.add(feature);
        return fc;
    }

    private static Polygon readJTSPolygon(ObjectMapper mapper, String geometryJson) throws IOException {
        Reader reader = new StringReader(geometryJson);
        Polygon polygon = mapper.readValue(reader, Polygon.class);
        return polygon;
    }
}
