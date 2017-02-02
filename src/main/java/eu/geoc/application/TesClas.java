package eu.geoc.application;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 01/02/2017.
 */

@XmlRootElement
public class TesClas {
    String geoJson;

    public TesClas() {
    }

    public TesClas(String geoJson) {
        this.geoJson = geoJson;
    }

    public String getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(String geoJson) {
        this.geoJson = geoJson;
    }
}
