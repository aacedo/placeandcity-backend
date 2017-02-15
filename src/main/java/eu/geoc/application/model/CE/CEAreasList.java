package eu.geoc.application.model.CE;

import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.AreasList;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public class CEAreasList extends AreasList {
    private CivicEngagement civicEngagement;
    private List<CEArea> areas;

    public CEAreasList() {
        super();
    }

    public CEAreasList(String id, CivicEngagement civicEngagement, List<CEArea> areas) {
        super(id);
        this.civicEngagement = civicEngagement;
        this.areas = areas;
    }

    public CivicEngagement getCivicEngagement() {
        return civicEngagement;
    }

    public void setCivicEngagement(CivicEngagement civicEngagement) {
        this.civicEngagement = civicEngagement;
    }

    public void setAreas(List<CEArea> areas) {
        this.areas = areas;
    }

    @Override
    public List<? extends BasicArea> getAreas() {
        return areas;
    }
}
