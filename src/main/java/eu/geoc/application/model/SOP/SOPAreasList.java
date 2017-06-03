package eu.geoc.application.model.SOP;

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
public class SOPAreasList extends AreasList {
    private List<SOPArea> areas;

    public SOPAreasList() {
        super();
        this.areas = new ArrayList<>();
    }

    public SOPAreasList(String id, List<SOPArea> areas) {
        super();
        this.areas = areas;
    }

    public void setAreas(List<SOPArea> areas) {
        this.areas = areas;
    }

    @Override
    public List<? extends BasicArea> getAreas() {
        return areas;
    }
}
