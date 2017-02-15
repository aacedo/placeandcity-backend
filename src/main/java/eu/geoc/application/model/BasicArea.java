package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public abstract class BasicArea {
    private Boolean livingIn;
    protected String layer;

    public BasicArea() {
    }

    public BasicArea(Boolean livingIn, String layer) {
        this.livingIn = livingIn;
        this.layer = layer;
    }

    public Boolean getLivingIn() {
        return livingIn;
    }

    public void setLivingIn(Boolean livingIn) {
        this.livingIn = livingIn;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }
}
