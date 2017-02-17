package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 17/02/2017.
 */

@XmlRootElement
public class LastData {
    private String id;
    private String mailUser;
    private String twitterName;

    public LastData() {
    }

    public LastData(String id, String mailUser, String twitterName) {
        this.id = id;
        this.mailUser = mailUser;
        this.twitterName = twitterName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }
}
