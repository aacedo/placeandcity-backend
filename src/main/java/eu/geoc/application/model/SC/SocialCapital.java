package eu.geoc.application.model.SC;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

@XmlRootElement
public class SocialCapital {
    private Integer bosc1;
    private Integer bosc2;
    private Integer brsc1;
    private Integer brsc2;

    public SocialCapital() {
    }

    public SocialCapital(Integer bosc1, Integer bosc2, Integer brsc1, Integer brsc2) {
        this.bosc1 = bosc1;
        this.bosc2 = bosc2;
        this.brsc1 = brsc1;
        this.brsc2 = brsc2;
    }

    public Integer getBosc1() {
        return bosc1;
    }

    public void setBosc1(Integer bosc1) {
        this.bosc1 = bosc1;
    }

    public Integer getBosc2() {
        return bosc2;
    }

    public void setBosc2(Integer bosc2) {
        this.bosc2 = bosc2;
    }

    public Integer getBrsc1() {
        return brsc1;
    }

    public void setBrsc1(Integer brsc1) {
        this.brsc1 = brsc1;
    }

    public Integer getBrsc2() {
        return brsc2;
    }

    public void setBrsc2(Integer brsc2) {
        this.brsc2 = brsc2;
    }
}
