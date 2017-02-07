package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

@XmlRootElement
public class SC extends Factor {
    private SocialCapital socialCapital;
    private SCDimensions dimensions;

    public SC() {
        super();
    }

    public SC(String layer, SocialCapital socialCapital, SCDimensions dimensions) {
        super(layer);
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
