package eu.geoc.application.services.model;

import eu.geoc.application.model.UserEntry;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 09/02/2017.
 */

@XmlRootElement
public class UserDetails implements UserEntryFiller{
    private Integer gender;
    private Integer age;
    private String country;
    private Integer study;
    private Integer profession;
    private Integer income;

    public UserDetails() {
    }

    public UserDetails(Integer gender, Integer age, String country, Integer study, Integer profession, Integer income) {
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.study = study;
        this.profession = profession;
        this.income = income;
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

    @Override
    public void fill(UserEntry userEntry) {
        userEntry.setGender(gender);
        userEntry.setAge(age);
        userEntry.setCountry(country);
        userEntry.setStudy(study);
        userEntry.setProfession(profession);
        userEntry.setIncome(income);
    }

    public static UserDetails getFromUserEntry(UserEntry ue){
        return new UserDetails(ue.getGender(),ue.getAge(),ue.getCountry(),
                ue.getStudy(),ue.getProfession(),ue.getIncome());
    }
}
