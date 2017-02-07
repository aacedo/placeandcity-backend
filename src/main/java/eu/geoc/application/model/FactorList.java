package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */


@XmlRootElement
public class FactorList {
    private String id;
    private List<Factor> factors;

    public FactorList() {
    }

    public FactorList(String id, List<Factor> factors) {
        this.id = id;
        this.factors = factors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }
}
