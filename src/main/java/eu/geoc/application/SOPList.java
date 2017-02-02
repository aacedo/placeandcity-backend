package eu.geoc.application;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 26/01/2017.
 */

@XmlRootElement
public class SOPList {
    private String id;
    private List<SOP> sops;

    public SOPList() {
    }

    public SOPList(String id, List<SOP> sops) {
        this.id = id;
        this.sops = sops;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SOP> getSops() {
        return sops;
    }

    public void setSops(List<SOP> sops) {
        this.sops = sops;
    }
}
