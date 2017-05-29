package eu.geoc.application.services.model;

import eu.geoc.application.model.SOP.SOPAreasList;
import eu.geoc.application.model.UserEntry;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 29/05/2017.
 */

public class SOPFiller implements UserEntryFiller{
    private SOPAreasList SOP;

    public SOPFiller(SOPAreasList SOP) {
        this.SOP = SOP;
    }

    @Override
    public void fill(UserEntry userEntry) {
        userEntry.setSOP(SOP);
    }
}
