package eu.geoc.application.services.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by albertacedosanchez on 19/1/17.
 */

@XmlRootElement
public class LayersResult {
    private String id;
    private String geoJson;

    public LayersResult() {

    }

    public LayersResult(String id, String geoJson) {
        this.id = id;
        this.geoJson = geoJson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(String geoJson) {
        this.geoJson = geoJson;
    }
}
