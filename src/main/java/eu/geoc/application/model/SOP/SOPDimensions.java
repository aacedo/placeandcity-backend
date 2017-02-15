package eu.geoc.application.model.SOP;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */

@XmlRootElement
public class SOPDimensions {
    private Boolean liveArea;
    private Integer pi1;
    private Integer pi2;
    private Integer pi3;
    private Integer pa1;
    private Integer pa2;
    private Integer pa3;
    private Integer pd1;
    private Integer pd2;
    private Integer pid3;

    public SOPDimensions() {
    }

    public SOPDimensions(Boolean liveArea, Integer pi1, Integer pi2, Integer pi3, Integer pa1, Integer pa2, Integer pa3, Integer pd1, Integer pd2, Integer pid3) {
        this.liveArea = liveArea;
        this.pi1 = pi1;
        this.pi2 = pi2;
        this.pi3 = pi3;
        this.pa1 = pa1;
        this.pa2 = pa2;
        this.pa3 = pa3;
        this.pd1 = pd1;
        this.pd2 = pd2;
        this.pid3 = pid3;
    }

    public Boolean isLiveArea() {
        return liveArea;
    }

    public void setLiveArea(Boolean liveArea) {
        this.liveArea = liveArea;
    }

    public Integer getPi1() {
        return pi1;
    }

    public void setPi1(Integer pi1) {
        this.pi1 = pi1;
    }

    public Integer getPi2() {
        return pi2;
    }

    public void setPi2(Integer pi2) {
        this.pi2 = pi2;
    }

    public Integer getPi3() {
        return pi3;
    }

    public void setPi3(Integer pi3) {
        this.pi3 = pi3;
    }

    public Integer getPa1() {
        return pa1;
    }

    public void setPa1(Integer pa1) {
        this.pa1 = pa1;
    }

    public Integer getPa2() {
        return pa2;
    }

    public void setPa2(Integer pa2) {
        this.pa2 = pa2;
    }

    public Integer getPa3() {
        return pa3;
    }

    public void setPa3(Integer pa3) {
        this.pa3 = pa3;
    }

    public Integer getPd1() {
        return pd1;
    }

    public void setPd1(Integer pd1) {
        this.pd1 = pd1;
    }

    public Integer getPd2() {
        return pd2;
    }

    public void setPd2(Integer pd2) {
        this.pd2 = pd2;
    }

    public Integer getPid3() {
        return pid3;
    }

    public void setPid3(Integer pid3) {
        this.pid3 = pid3;
    }
}
