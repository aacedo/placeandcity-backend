package eu.geoc.application.model;

import java.util.List;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 29/05/2017.
 */

public class UserEntry {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntry() {
    }

    public UserEntry(String id, AreasList SOP, AreasList SC, AreasList CE, boolean home, boolean portugal, Integer freguesia, Integer howlong, String zip, List<Integer> problem, String date, Integer gender, Integer age, String country, Integer study, Integer profession, Integer income, String mailUser, String twitterName, Integer di1, Integer dm1, String comment) {
        this.id = id;
        this.SOP = SOP;
        this.SC = SC;
        this.CE = CE;
        this.home = home;
        this.portugal = portugal;
        this.freguesia = freguesia;
        this.howlong = howlong;
        this.zip = zip;
        this.problem = problem;
        this.date = date;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.study = study;
        this.profession = profession;
        this.income = income;
        this.mailUser = mailUser;
        this.twitterName = twitterName;
        this.di1 = di1;
        this.dm1 = dm1;
        this.comment = comment;
    }

    //region Areas
    private AreasList SOP;
    private AreasList SC;
    private AreasList CE;

    public AreasList getSOP() {
        return SOP;
    }

    public void setSOP(AreasList SOP) {
        this.SOP = SOP;
    }

    public AreasList getSC() {
        return SC;
    }

    public void setSC(AreasList SC) {
        this.SC = SC;
    }

    public AreasList getCE() {
        return CE;
    }

    public void setCE(AreasList CE) {
        this.CE = CE;
    }

    //endregion

    //region FirstData
    private Boolean home;
    private Boolean portugal;
    private Integer freguesia;
    private Integer howlong;
    private String zip;
    private List<Integer> problem;
    private String date;

    public Boolean isHome() {
        return home;
    }

    public void setHome(Boolean home) {
        this.home = home;
    }

    public Boolean isPortugal() {
        return portugal;
    }

    public void setPortugal(Boolean portugal) {
        this.portugal = portugal;
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
    //endregion

    //region UserDetails
    private Integer gender;
    private Integer age;
    private String country;
    private Integer study;
    private Integer profession;
    private Integer income;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getStudy() {
        return study;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    public Integer getProfession() {
        return profession;
    }

    public void setProfession(Integer profession) {
        this.profession = profession;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
    //endregion

    //region LastData
    private String mailUser;
    private String twitterName;

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }
    //endregion

    //region FinalComments
    private Integer di1;
    private Integer dm1;
    private String comment;

    public Integer getDi1() {
        return di1;
    }

    public void setDi1(Integer di1) {
        this.di1 = di1;
    }

    public Integer getDm1() {
        return dm1;
    }

    public void setDm1(Integer dm1) {
        this.dm1 = dm1;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    //endregion
}
