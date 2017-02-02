package eu.geoc.application;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by albertacedosanchez on 18/1/17.
 */



@XmlRootElement
public class FirstData {
    private boolean home;
    private int freguesia;
    private int howlong;
    private String zip;
    private List<Integer> problem;


    public FirstData() {
    }

    public FirstData(boolean home, int freguesia, int howlong, String zip, List<Integer> problem) {
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

    public int getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(int freguesia) {
        this.freguesia = freguesia;
    }

    public int getHowlong() {
        return howlong;
    }

    public void setHowlong(int howlong) {
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