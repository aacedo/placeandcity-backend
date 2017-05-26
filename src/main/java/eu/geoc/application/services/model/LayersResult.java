package eu.geoc.application.services.model;

import org.geojson.FeatureCollection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by albertacedosanchez on 19/1/17.
 */

@XmlRootElement
public class LayersResult extends IdResult{
    private FeatureCollection geoJson;

    public LayersResult() {
        super();
    }

    public LayersResult(String id, FeatureCollection geoJson) {
        super(id);
        this.geoJson = geoJson;
    }

    public FeatureCollection getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(FeatureCollection geoJson) {
        this.geoJson = geoJson;
    }
}
