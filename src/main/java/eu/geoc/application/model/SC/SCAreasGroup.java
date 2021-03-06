package eu.geoc.application.model.SC;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 15/02/2017.
 */

@XmlRootElement
public class SCAreasGroup {
    private String name;
    private SCDimensions dimensions;
    private List<SCArea> areas;
    private String nature;

    public SCAreasGroup() {
        this.areas = new ArrayList<>();
    }

    public SCAreasGroup(String name, List<SCArea> areas, SCDimensions dimensions, String nature) {
        this.name = name;
        this.areas = areas;
        this.dimensions = dimensions;
        this.nature = nature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SCArea> getAreas() {
        return areas;
    }

    public void setAreas(List<SCArea> areas) {
        this.areas = areas;
    }

    public SCDimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(SCDimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}
