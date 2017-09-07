package eu.geoc.application.services.model;

import eu.geoc.application.model.AreasList;
import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.CE.CEAreasList;
import eu.geoc.application.model.UserEntry;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 29/05/2017.
 */

public class CEFiller implements UserEntryFiller{
    private CEAreasList CE;

    public CEFiller(CEAreasList CE) {
        this.CE = CE;
    }

    @Override
    public void fill(UserEntry userEntry) {
        int count = 0;
        for (BasicArea area : CE.getAreas()) {
            area.setId(userEntry.getId() + "CE" + String.valueOf(++count));
        }
        userEntry.setCE(CE);
    }

    public static CEAreasList getFromUserEntry(UserEntry ue){
        return (CEAreasList) ue.getCE();
    }
}
