package eu.geoc.application.model;

import org.geojson.FeatureCollection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public abstract class BasicArea {
    private Boolean livingIn;
    protected FeatureCollection layer;

    public BasicArea() {
    }

    public BasicArea(Boolean livingIn, FeatureCollection layer) {
        this.livingIn = livingIn;
        this.layer = layer;
    }

    public Boolean getLivingIn() {
        return livingIn;
    }

    public void setLivingIn(Boolean livingIn) {
        this.livingIn = livingIn;
    }

    public FeatureCollection getLayer() {
        return layer;
    }

    public void setLayer(FeatureCollection layer) {
        this.layer = layer;
    }
}
