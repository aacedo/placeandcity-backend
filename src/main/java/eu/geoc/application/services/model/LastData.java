package eu.geoc.application.services.model;

import eu.geoc.application.model.UserEntry;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 17/02/2017.
 */

@XmlRootElement
public class LastData implements UserEntryFiller{
    private String mailUser;
    private String twitterName;

    public LastData() {
    }

    public LastData(String mailUser, String twitterName) {
        this.mailUser = mailUser;
        this.twitterName = twitterName;
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

    @Override
    public void fill(UserEntry userEntry) {
        userEntry.setMailUser(mailUser);
        userEntry.setTwitterName(twitterName);
    }
}
