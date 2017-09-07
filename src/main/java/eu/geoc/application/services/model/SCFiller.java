package eu.geoc.application.services.model;

import eu.geoc.application.model.AreasList;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.SC.SCArea;
import eu.geoc.application.model.SC.SCAreasGroup;
import eu.geoc.application.model.SC.SCAreasList;
import eu.geoc.application.model.UserEntry;

import java.util.List;

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
        int count = 0;
        for (SCAreasGroup group : SC.getGroups()) {
            for (SCArea area : group.getAreas()) {
                area.setId(userEntry.getId() + "SC" + String.valueOf(++count));
            }
        }
        userEntry.setSC(SC);
    }

    public static SCAreasList getFromUserEntry(UserEntry ue){
        return (SCAreasList) ue.getSC();
    }
}
