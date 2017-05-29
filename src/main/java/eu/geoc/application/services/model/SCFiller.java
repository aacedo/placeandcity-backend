package eu.geoc.application.services.model;

import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.UserEntry;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 29/05/2017.
 */

public class SCFiller implements UserEntryFiller{
    private SCAreasList SC;

    public SCFiller(SCAreasList SC) {
        this.SC = SC;
    }

    @Override
    public void fill(UserEntry userEntry) {
        userEntry.setSC(SC);
    }
}
