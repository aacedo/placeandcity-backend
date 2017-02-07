package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

@XmlRootElement
public class CE extends Factor {
    private CivicEngagement civicEngagement;

    public CE() {
        super();
    }

    public CE(String layer, CivicEngagement civicEngagement) {
        super(layer);
        this.civicEngagement = civicEngagement;
    }

    public CivicEngagement getCivicEngagement() {
        return civicEngagement;
    }

    public void setCivicEngagement(CivicEngagement civicEngagement) {
        this.civicEngagement = civicEngagement;
    }
}
