package eu.geoc.application;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */

@XmlRootElement
public class SOP {
    private String layer;
    private SOPPredictors predictors;
    private SOPDimensions dimensions;

    public SOP() {
    }

    public SOP(String layer, SOPPredictors predictors, SOPDimensions dimensions) {
        this.layer = layer;
        this.predictors = predictors;
        this.dimensions = dimensions;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public SOPPredictors getPredictors() {
        return predictors;
    }

    public void setPredictors(SOPPredictors predictors) {
        this.predictors = predictors;
    }

    public SOPDimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(SOPDimensions dimensions) {
        this.dimensions = dimensions;
    }
}
