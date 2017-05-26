package eu.geoc.application.util;

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
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 02/02/2017.
 */

public class GeoJsonOperations {
    public enum Operations{
        UNION, INTERSECTION, DIFFERENCE
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
