package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.geom.Area;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */


@XmlRootElement
public abstract class AreasList {
    protected String id;

    public AreasList() {
    }

    public AreasList(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract List<? extends BasicArea> getAreas();
}
