package eu.geoc.application.util;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Polygon;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.CE.CEArea;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.SC.SCArea;
import eu.geoc.application.model.SC.SCAreasGroup;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.SOP.SOPArea;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 02/02/2017.
 */

public class GeoJsonOperations {

    public static final String ID_KEY = "Id";
    public static final String TYPE_KEY = "TypeKey";
    public static final String DATE_KEY = "DateKey";
    public static final String EMAIL_KEY = "Email";
    public static final String HOW_LONG_KEY = "Howlong";
    public static final String FREGUESIA_KEY = "Freguesia";
    public static final String ZIP_CODE_KEY = "ZipCode";
    public static final String COUNTRY_KEY = "Country";
    public static final String HOME_KEY = "Home";
    public static final String AGE_KEY = "Age";
    public static final String GENDER_KEY = "Gender";
    public static final String PROFESSION_KEY = "Profession";
    public static final String TWITTER_KEY = "Twitter";
    public static final String PORTUGAL_KEY = "Portugal";
    public static final String INCOME_KEY = "Income";
    public static final String PROBLEM_KEY = "Problem";
    public static final String NAME_KEY = "NameKey";


    public static final String USER_TYPE = "user_type";


    public static final String SOP_TYPE_VALUE = "SOP";
    public static final String SOP_COUNT = "SopCount";
    public static final String NA_1_KEY = "Na1";
    public static final String NA_2_KEY = "Na2";
    public static final String NA_3_KEY = "Na3";
    public static final String NA_4_KEY = "Na4";
    public static final String PI_1_KEY = "Pi1";
    public static final String PI_2_KEY = "Pi2";
    public static final String PI_3_KEY = "Pi3";
    public static final String PA_1_KEY = "Pa1";
    public static final String PA_2_KEY = "Pa2";
    public static final String PA_3_KEY = "Pa3";
    public static final String PD_1_KEY = "Pd1";
    public static final String PD_2_KEY = "Pd2";
    public static final String PD_3_KEY = "Pd3";


    public static final String SC_TYPE_VALUE = "SC";
    public static final String SC_COUNT = "ScCount";
    public static final String BOSC_1_KEY = "bosc1";
    public static final String BOSC_2_KEY = "bosc2";
    public static final String BRSC_1_KEY = "brsc1";
    public static final String BRSC_2_KEY = "brsc2";

    public static final String CE_TYPE_VALUE = "CE";
    public static final String CE_COUNT = "CeCount";
    public static final String GROUP_NAME_KEY = "GroupName";
    public static final String CEE_1_KEY = "Cee1";
    public static final String CEE_2_KEY = "Cee2";
    public static final String CEE_3_KEY = "Cee3";
    public static final String CP_1_KEY = "Cp1";
    public static final String CP_2_KEY = "Cp2";
    public static final String CP_3_KEY = "Cp3";
    public static final String N_1_KEY = "N1";
    public static final String N_2_KEY = "N2";
    public static final String N_3_KEY = "N3";
    public static final String SC_1_KEY = "Sc1";
    public static final String SC_2_KEY = "Sc2";
    public static final String SC_3_KEY = "Sc3";
    public static final String LIVING_IN_KEY = "LivingIn";
    public static final String CE_1_KEY = "Ce1";
    public static final String CE_2_KEY = "Ce2";
    public static final String CE_3_KEY = "Ce3";

    public enum Operations{
        UNION, INTERSECTION, DIFFERENCE
    }

    public static HashMap<String, FeatureCollection> getGeoJsonJoinEntriesPerID(List<UserEntry> userEntries) throws IOException {
        HashMap<String, FeatureCollection> organized = new HashMap<>();
        for (UserEntry userEntry : userEntries) {
            FeatureCollection userGeoJsons = joinGeoJson(getUserFeatureCollections(userEntry));
            if (userGeoJsons.getFeatures().size() > 0) {
                organized.put(userEntry.getDate().replace(":", "-").replace("+", "-"), userGeoJsons);
            }
        }
        return organized;
    }



    public static FeatureCollection getGeoJsonJoinEntries(List<UserEntry> userEntries) throws IOException {
        List<FeatureCollection> geoJsonList = new ArrayList<>();
        for (UserEntry userEntry : userEntries) {
            List<FeatureCollection> userGeoJsonList = getUserFeatureCollections(userEntry);
            geoJsonList.addAll(userGeoJsonList);
        }
        return joinGeoJson(geoJsonList);
    }

    private static List<FeatureCollection> getUserFeatureCollections(UserEntry userEntry) {
        int sopAreasCount = 0, scAreasCount = 0, ceAreasCount = 0;
        List<FeatureCollection> userGeoJsonList = new ArrayList<>();

        SOPAreasList sop = (SOPAreasList) userEntry.getSOP();
        SCAreasList sc = (SCAreasList) userEntry.getSC();
        CEAreasList ce = (CEAreasList) userEntry.getCE();

        //region areas counting
        if(sop != null) {
            for (BasicArea basicArea : sop.getAreas()) {
                SOPArea sopArea = (SOPArea) basicArea;
                sopAreasCount += sopArea.getLayer().getFeatures().size();
            }
        }
        if (sc != null) {
            for (SCAreasGroup group : sc.getGroups()) {
                for (SCArea scArea : group.getAreas()) {
                    scAreasCount += scArea.getLayer().getFeatures().size();
                }
            }
        }
        if(ce != null) {
            for (BasicArea basicArea : ce.getAreas()) {
                CEArea ceArea = (CEArea) basicArea;
                ceAreasCount += ceArea.getLayer().getFeatures().size();
            }
        }
        //endregion

        //region userType determination
        int sopBit = (sopAreasCount > 0) ? 16 : 0;
        int scBit = (scAreasCount > 0) ? 8 : 0;
        int ceBit = (ceAreasCount > 0) ? 4 : 0;
        int emailTwitterBit = (userEntry.getMailUser() != null && !userEntry.getMailUser().isEmpty()) ||
                (userEntry.getTwitterName() != null && !userEntry.getTwitterName().isEmpty())? 2 : 0;
        int freguesiaBit = (userEntry.getFreguesia() != null && userEntry.getFreguesia() > 0) ? 1 : 0;
        int userType = sopBit | scBit | ceBit | emailTwitterBit | freguesiaBit;
        //endregion

        if(sop != null) {
            for (BasicArea basicArea : sop.getAreas()) {
                SOPArea sopArea = (SOPArea) basicArea;
                FeatureCollection layer = sopArea.getLayer();
                for (Feature feature : layer) {
                    Map<String, Object> properties = feature.getProperties();
                    putBasicProperties(SOP_TYPE_VALUE, userEntry, properties);
                    conditionalPut(properties, SOP_COUNT, sopAreasCount);
                    conditionalPut(properties, USER_TYPE, userType);

                    conditionalPut(properties, NAME_KEY, sopArea.getName());
                    conditionalPut(properties, LIVING_IN_KEY, sopArea.getLivingIn());

                    if (sopArea.getPredictors() != null) {
                        conditionalPut(properties, NA_1_KEY, sopArea.getPredictors().getNa1());
                        conditionalPut(properties, NA_2_KEY, sopArea.getPredictors().getNa2());
                        conditionalPut(properties, NA_3_KEY, sopArea.getPredictors().getNa3());
                        conditionalPut(properties, NA_4_KEY, sopArea.getPredictors().getNa4());
                    }

                    if (sopArea.getDimensions() != null) {
                        conditionalPut(properties, PI_1_KEY, sopArea.getDimensions().getPi1());
                        conditionalPut(properties, PI_2_KEY, sopArea.getDimensions().getPi2());
                        conditionalPut(properties, PI_3_KEY, sopArea.getDimensions().getPi3());
                        conditionalPut(properties, PA_1_KEY, sopArea.getDimensions().getPa1());
                        conditionalPut(properties, PA_2_KEY, sopArea.getDimensions().getPa2());
                        conditionalPut(properties, PA_3_KEY, sopArea.getDimensions().getPa3());
                        conditionalPut(properties, PD_1_KEY, sopArea.getDimensions().getPd1());
                        conditionalPut(properties, PD_2_KEY, sopArea.getDimensions().getPd2());
                        conditionalPut(properties, PD_3_KEY, sopArea.getDimensions().getPd3());
                    }
                }
                userGeoJsonList.add(layer);
            }
        }


        if (sc != null) {
            for (SCAreasGroup group : sc.getGroups()) {
                for (SCArea scArea : group.getAreas()) {
                    FeatureCollection layer = scArea.getLayer();
                    for (Feature feature : layer) {
                        Map<String, Object> properties = feature.getProperties();
                        putBasicProperties(SC_TYPE_VALUE, userEntry, properties);
                        conditionalPut(properties, SC_COUNT, scAreasCount);
                        conditionalPut(properties, USER_TYPE, userType);

                        conditionalPut(properties, LIVING_IN_KEY, scArea.getLivingIn());

                        if (scArea.getSocialCapital() != null) {
                            conditionalPut(properties, BOSC_1_KEY, scArea.getSocialCapital().getBosc1());
                            conditionalPut(properties, BOSC_2_KEY, scArea.getSocialCapital().getBosc2());
                            conditionalPut(properties, BRSC_1_KEY, scArea.getSocialCapital().getBrsc1());
                            conditionalPut(properties, BRSC_2_KEY, scArea.getSocialCapital().getBrsc2());
                        }

                        conditionalPut(properties, GROUP_NAME_KEY, group.getName());
                        if (group.getDimensions() != null) {
                            conditionalPut(properties, CEE_1_KEY, group.getDimensions().getCee1());
                            conditionalPut(properties, CEE_2_KEY, group.getDimensions().getCee2());
                            conditionalPut(properties, CEE_3_KEY, group.getDimensions().getCee3());
                            conditionalPut(properties, CP_1_KEY, group.getDimensions().getCp1());
                            conditionalPut(properties, CP_2_KEY, group.getDimensions().getCp2());
                            conditionalPut(properties, CP_3_KEY, group.getDimensions().getCp3());
                            conditionalPut(properties, N_1_KEY, group.getDimensions().getN1());
                            conditionalPut(properties, N_2_KEY, group.getDimensions().getN2());
                            conditionalPut(properties, N_3_KEY, group.getDimensions().getN3());
                            conditionalPut(properties, SC_1_KEY, group.getDimensions().getSc1());
                            conditionalPut(properties, SC_2_KEY, group.getDimensions().getSc2());
                            conditionalPut(properties, SC_3_KEY, group.getDimensions().getSc3());
                        }
                    }
                    userGeoJsonList.add(layer);
                }
            }
        }


        if(ce != null) {
            for (BasicArea basicArea : ce.getAreas()) {
                CEArea ceArea = (CEArea) basicArea;
                FeatureCollection layer = ceArea.getLayer();
                for (Feature feature : layer) {
                    Map<String, Object> properties = feature.getProperties();
                    putBasicProperties(CE_TYPE_VALUE, userEntry, properties);
                    conditionalPut(properties, CE_COUNT, ceAreasCount);
                    conditionalPut(properties, USER_TYPE, userType);

                    conditionalPut(properties, LIVING_IN_KEY, ceArea.getLivingIn());

                    if (ce.getCivicEngagement() != null) {
                        conditionalPut(properties, CE_1_KEY, ce.getCivicEngagement().getCe1());
                        conditionalPut(properties, CE_2_KEY, ce.getCivicEngagement().getCe2());
                        conditionalPut(properties, CE_3_KEY, ce.getCivicEngagement().getCe3());
                    }
                }
                userGeoJsonList.add(layer);
            }
        }


        return userGeoJsonList;
    }

    private static void conditionalPut(Map<String, Object> properties, String key, Object object){
        if(object != null) properties.put(key, object.toString());
    }

    private static void putBasicProperties(String typeValue, UserEntry userEntry, Map<String, Object> properties) {
        conditionalPut(properties, TYPE_KEY, typeValue);
        conditionalPut(properties, ID_KEY, userEntry.getId());
        conditionalPut(properties, DATE_KEY, userEntry.getDate());
        conditionalPut(properties, EMAIL_KEY, userEntry.getMailUser());
        conditionalPut(properties, HOW_LONG_KEY, userEntry.getHowlong());
        conditionalPut(properties, FREGUESIA_KEY, userEntry.getFreguesia());
        conditionalPut(properties, ZIP_CODE_KEY, userEntry.getZip());
        conditionalPut(properties, COUNTRY_KEY, userEntry.getCountry());
        conditionalPut(properties, HOME_KEY, userEntry.isHome());
        conditionalPut(properties, AGE_KEY, userEntry.getAge());
        conditionalPut(properties, GENDER_KEY, userEntry.getGender());
        conditionalPut(properties, PROFESSION_KEY, userEntry.getProfession());
        conditionalPut(properties, TWITTER_KEY, userEntry.getTwitterName());
        conditionalPut(properties, PORTUGAL_KEY, userEntry.isPortugal());
        conditionalPut(properties, INCOME_KEY, userEntry.getIncome());
        conditionalPut(properties, PROBLEM_KEY, userEntry.getProblem());
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
        return createFeatureCollection(mapper, result);
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
        return mapper.readValue(reader, Polygon.class);
    }
}
