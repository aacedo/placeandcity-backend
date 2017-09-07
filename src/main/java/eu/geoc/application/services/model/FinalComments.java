package eu.geoc.application.services.model;

import eu.geoc.application.model.UserEntry;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 18/05/2017.
 */

@XmlRootElement
public class FinalComments implements UserEntryFiller{
    private Integer di1;
    private Integer dm1;
    private String comment;

    public FinalComments() {
    }

    public FinalComments(Integer di1, Integer dm1, String comment) {
        this.di1 = di1;
        this.dm1 = dm1;
        this.comment = comment;
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

    @Override
    public void fill(UserEntry userEntry) {
        userEntry.setDi1(di1);
        userEntry.setDm1(dm1);
        userEntry.setComment(comment);
    }

    public static FinalComments getFromUserEntry(UserEntry ue){
        return new FinalComments(ue.getDi1(), ue.getDm1(), ue.getComment());
    }
}
