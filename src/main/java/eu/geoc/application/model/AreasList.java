package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */


@XmlRootElement
public abstract class AreasList {

    public AreasList() {
    }

    public abstract List<? extends BasicArea> getAreas();
}
