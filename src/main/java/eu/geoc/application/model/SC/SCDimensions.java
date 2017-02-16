package eu.geoc.application.model.SC;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 06/02/2017.
 */

@XmlRootElement
public class SCDimensions {
    private Integer sc1;
    private Integer sc2;
    private Integer sc3;
    private Integer n1;
    private Integer n2;
    private Integer n3;
    private Integer cee1;
    private Integer cee2;
    private Integer cee3;
    private Integer cp1;
    private Integer cp2;
    private Integer cp3;

    public SCDimensions() {
    }

    public SCDimensions(Integer sc1, Integer sc2, Integer sc3, Integer n1, Integer n2, Integer n3, Integer cee1, Integer cee2, Integer cee3, Integer cp1, Integer cp2, Integer cp3) {
        this.sc1 = sc1;
        this.sc2 = sc2;
        this.sc3 = sc3;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.cee1 = cee1;
        this.cee2 = cee2;
        this.cee3 = cee3;
        this.cp1 = cp1;
        this.cp2 = cp2;
        this.cp3 = cp3;
    }

    public Integer getSc1() {
        return sc1;
    }

    public void setSc1(Integer sc1) {
        this.sc1 = sc1;
    }

    public Integer getSc2() {
        return sc2;
    }

    public void setSc2(Integer sc2) {
        this.sc2 = sc2;
    }

    public Integer getSc3() {
        return sc3;
    }

    public void setSc3(Integer sc3) {
        this.sc3 = sc3;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
    }

    public Integer getN3() {
        return n3;
    }

    public void setN3(Integer n3) {
        this.n3 = n3;
    }

    public Integer getCee1() {
        return cee1;
    }

    public void setCee1(Integer cee1) {
        this.cee1 = cee1;
    }

    public Integer getCee2() {
        return cee2;
    }

    public void setCee2(Integer cee2) {
        this.cee2 = cee2;
    }

    public Integer getCee3() {
        return cee3;
    }

    public void setCee3(Integer cee3) {
        this.cee3 = cee3;
    }

    public Integer getCp1() {
        return cp1;
    }

    public void setCp1(Integer cp1) {
        this.cp1 = cp1;
    }

    public Integer getCp2() {
        return cp2;
    }

    public void setCp2(Integer cp2) {
        this.cp2 = cp2;
    }

    public Integer getCp3() {
        return cp3;
    }

    public void setCp3(Integer cp3) {
        this.cp3 = cp3;
    }
}
