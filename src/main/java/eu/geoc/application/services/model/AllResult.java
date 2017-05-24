package eu.geoc.application.services.model;

import eu.geoc.application.model.AreasList;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 24/05/2017.
 */

@XmlRootElement
public class AllResult {
    List<AreasList> all;

    public AllResult() {
    }

    public AllResult(List<AreasList> all) {
        this.all = all;
    }

    public List<AreasList> getAll() {
        return all;
    }

    public void setAll(List<AreasList> all) {
        this.all = all;
    }
}
