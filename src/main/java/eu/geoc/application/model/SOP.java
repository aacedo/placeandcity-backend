package eu.geoc.application.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */

@XmlRootElement
public class SOP extends Factor {
    private SOPPredictors predictors;
    private SOPDimensions dimensions;

    public SOP() {
        super();
    }

    public SOP(String layer, SOPPredictors predictors, SOPDimensions dimensions) {
        super(layer);
        this.predictors = predictors;
        this.dimensions = dimensions;
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
