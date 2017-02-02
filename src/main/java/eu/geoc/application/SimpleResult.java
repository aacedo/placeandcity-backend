package eu.geoc.application;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by albertacedosanchez on 18/1/17.
 */
@XmlRootElement
public class SimpleResult {
    private boolean result;
    private String description;

    public SimpleResult() {
    }

    public SimpleResult(boolean result, String description) {
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