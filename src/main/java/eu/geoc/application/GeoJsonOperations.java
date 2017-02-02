package eu.geoc.application;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Polygon;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 02/02/2017.
 */

public class GeoJsonOperations {
    public enum Operations{
        UNION, INTERSECTION, DIFFERENCE
    }

    public static String joinGeoJson(List<String> geoJsonList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        FeatureCollection nfc = new FeatureCollection();
        for (String geoJson : geoJsonList) {
            GeoJsonObject geoJsonObject = new ObjectMapper().readValue(prepareString(geoJson), GeoJsonObject.class);
            if (geoJsonObject instanceof FeatureCollection){
                FeatureCollection fc = (FeatureCollection)geoJsonObject;
                for (Feature feature : fc) {
                    nfc.add(feature);
                }
            }
        }
        String resultString = new ObjectMapper().writeValueAsString(nfc);
        return resultString;
    }

    private static String prepareString(String geoJson) {
        return geoJson.replace("\'", "\"");
    }

    public static String applyOp(List<String> geoJsonList, Operations operation) throws IOException {
        String resultString = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        com.vividsolutions.jts.geom.Geometry result = null;

        for (String geoJson : geoJsonList) {
            String geoJsonString = prepareString(geoJson);
            List<String> geometriesJson = getGeometriesJson(mapper, geoJsonString);
            for (String geometryJson : geometriesJson) {
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

            FeatureCollection fc = createFeatureCollection(mapper, result);
            resultString= new ObjectMapper().writeValueAsString(fc);
        }
        return resultString;
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

    private static List<String> getGeometriesJson(ObjectMapper mapper, String geoJsonString) throws IOException {
        List<String> geometriesJson = new ArrayList<>();
        GeoJsonObject geoJsonObject = mapper.readValue(geoJsonString, GeoJsonObject.class);
        if (geoJsonObject instanceof FeatureCollection){
            FeatureCollection fc = (FeatureCollection)geoJsonObject;
            for (Feature feature : fc) {
                String json = new ObjectMapper().writeValueAsString(feature.getGeometry());
                geometriesJson.add(json);
            }
        }
        return geometriesJson;
    }

    private static Polygon readJTSPolygon(ObjectMapper mapper, String geometryJson) throws IOException {
        Reader reader = new StringReader(geometryJson);
        Polygon polygon = mapper.readValue(reader, Polygon.class);
        return polygon;
    }
}
