package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

@XmlRootElement
public class CivicEngagement {
    private Integer ce1;
    private Integer ce2;
    private Integer ce3;

    public CivicEngagement() {
    }

    public CivicEngagement(Integer ce1, Integer ce2, Integer ce3) {
        this.ce1 = ce1;
        this.ce2 = ce2;
        this.ce3 = ce3;
    }

    public Integer getCe1() {
        return ce1;
    }

    public void setCe1(Integer ce1) {
        this.ce1 = ce1;
    }

    public Integer getCe2() {
        return ce2;
    }

    public void setCe2(Integer ce2) {
        this.ce2 = ce2;
    }

    public Integer getCe3() {
        return ce3;
    }

    public void setCe3(Integer ce3) {
        this.ce3 = ce3;
    }
}
