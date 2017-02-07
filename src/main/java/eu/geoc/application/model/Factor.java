package eu.geoc.application.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.*;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

@XmlRootElement
public abstract class Factor {
    protected String layer;

    public Factor() {
    }

    public Factor(String layer) {
        this.layer = layer;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }
}
