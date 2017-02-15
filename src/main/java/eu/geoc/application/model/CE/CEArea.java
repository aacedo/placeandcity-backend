package eu.geoc.application.model.CE;

import eu.geoc.application.model.BasicArea;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public class CEArea extends BasicArea {

    public CEArea() {
        super();
    }

    public CEArea(Boolean livingIn, String layer) {
        super(livingIn, layer);
    }
}
