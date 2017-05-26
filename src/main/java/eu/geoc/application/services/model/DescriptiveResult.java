package eu.geoc.application.services.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by albertacedosanchez on 18/1/17.
 */

@XmlRootElement
public class DescriptiveResult extends IdResult{
    private boolean result;
    private String description;

    public DescriptiveResult() {
        super();
    }

    public DescriptiveResult(String id, boolean result, String description) {
        super(id);
        this.result = result;
        this.description = description;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}