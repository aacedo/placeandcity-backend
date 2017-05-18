package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 18/05/2017.
 */

@XmlRootElement
public class FinalComments {
    private String id;
    private Integer di1;
    private Integer dm1;
    private String comment;

    public FinalComments() {
    }

    public FinalComments(String id, Integer di1, Integer dm1, String comment) {
        this.id = id;
        this.di1 = di1;
        this.dm1 = dm1;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDi1() {
        return di1;
    }

    public void setDi1(Integer di1) {
        this.di1 = di1;
    }

    public Integer getDm1() {
        return dm1;
    }

    public void setDm1(Integer dm1) {
        this.dm1 = dm1;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
