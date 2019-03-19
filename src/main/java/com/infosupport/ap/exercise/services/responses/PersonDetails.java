package com.infosupport.ap.exercise.services.responses;

public class PersonDetails {
    private String name;
    private String userdata;

    public PersonDetails() {
    }

    public PersonDetails(String name, String userdata) {
        this.name = name;
        this.userdata = userdata;
    }

    public String getName() {
        return name;
    }

    public String getUserdata() {
        return userdata;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "name='" + name + '\'' +
                ", userdata='" + userdata + '\'' +
                '}';
    }
}
