package eu.geoc.application.model.SC;

import eu.geoc.application.model.BasicArea;
import eu.geoc.application.model.AreasList;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public class SCAreasList extends AreasList {
    private List<SCAreasGroup> groups;

    public SCAreasList() {
        this.groups = new ArrayList<>();
    }

    public SCAreasList(String id, List<SCAreasGroup> groups) {
        super();
        this.groups = groups;
    }

    public List<SCAreasGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SCAreasGroup> groups) {
        this.groups = groups;
    }

    @Override
    public List<? extends BasicArea> getAreas() {
        List<SCArea> areas = new ArrayList<>();
        for (SCAreasGroup group : groups) {
            areas.addAll(group.getAreas());
        }
        return areas;
    }
}
