package eu.geoc.application.services.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by albertacedosanchez on 19/1/17.
 */

@XmlRootElement
public class IdResult {
    private String id;

    public IdResult() {

    }

    public IdResult(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
