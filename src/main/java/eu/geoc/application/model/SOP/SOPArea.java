package eu.geoc.application.model.SOP;

import eu.geoc.application.model.BasicArea;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public class SOPArea extends BasicArea {
    private SOPPredictors predictors;
    private SOPDimensions dimensions;

    public SOPArea() {
        super();
    }

    public SOPArea(Boolean livingIn, String layer, SOPPredictors predictors, SOPDimensions dimensions) {
        super(livingIn, layer);
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