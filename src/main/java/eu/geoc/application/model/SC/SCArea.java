package eu.geoc.application.model.SC;

import eu.geoc.application.model.BasicArea;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public class SCArea extends BasicArea {
    private SocialCapital socialCapital;
    private SCDimensions dimensions;

    public SCArea() {
        super();
    }

    public SCArea(Boolean livingIn, String layer, SocialCapital socialCapital, SCDimensions dimensions) {
        super(livingIn, layer);
        this.socialCapital = socialCapital;
        this.dimensions = dimensions;
    }

    public SocialCapital getSocialCapital() {
        return socialCapital;
    }

    public void setSocialCapital(SocialCapital socialCapital) {
        this.socialCapital = socialCapital;
    }

    public SCDimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(SCDimensions dimensions) {
        this.dimensions = dimensions;
    }
}
