package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by albertacedosanchez on 18/1/17.
 */

@XmlRootElement
public class FirstData {
    private boolean home;
    private Integer freguesia;
    private Integer howlong;
    private String zip;
    private List<Integer> problem;


    public FirstData() {
    }

    public FirstData(boolean home, Integer freguesia, Integer howlong, String zip, List<Integer> problem) {
        this.home = home;
        this.freguesia = freguesia;
        this.howlong = howlong;
        this.zip = zip;
        this.problem = problem;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public Integer getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(Integer freguesia) {
        this.freguesia = freguesia;
    }

    public Integer getHowlong() {
        return howlong;
    }

    public void setHowlong(Integer howlong) {
        this.howlong = howlong;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<Integer> getProblem() {
        return problem;
    }

    public void setProblem(List<Integer> problem) {
        this.problem = problem;
    }
}