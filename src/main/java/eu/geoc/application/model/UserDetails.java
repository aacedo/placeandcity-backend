package eu.geoc.application.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 09/02/2017.
 */

@XmlRootElement
public class UserDetails {
    private String id;
    private Integer gender;
    private Integer age;
    private String country;
    private Integer study;
    private Integer profession;
    private Integer income;

    public UserDetails() {
    }

    public UserDetails(String id, Integer gender, Integer age, String country, Integer study, Integer profession, Integer income) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.study = study;
        this.profession = profession;
        this.income = income;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
