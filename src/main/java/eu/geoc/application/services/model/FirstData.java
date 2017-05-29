package eu.geoc.application.services.model;

import eu.geoc.application.model.UserEntry;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by albertacedosanchez on 18/1/17.
 */

@XmlRootElement
public class FirstData implements UserEntryFiller{
    private boolean home;
    private Integer freguesia;
    private Integer howlong;
    private String zip;
    private List<Integer> problem;
    private String date;

    public FirstData() {
    }

    public FirstData(boolean home, Integer freguesia, Integer howlong, String zip, List<Integer> problem, String date) {
        this.home = home;
        this.freguesia = freguesia;
        this.howlong = howlong;
        this.zip = zip;
        this.problem = problem;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void fill(UserEntry userEntry) {
        userEntry.setHome(home);
        userEntry.setFreguesia(freguesia);
        userEntry.setHowlong(howlong);
        userEntry.setZip(zip);
        userEntry.setProblem(problem);
        userEntry.setDate(date);
    }
}