package com.infosupport.ap.exercise.models;

public class RegistrationImage {
    private Long classId;
    private String image;

    public RegistrationImage() {
    }

    public RegistrationImage(Long classId, String image) {
        this.classId = classId;
        this.image = image;
    }

    public Long getClassId() {
        return classId;
    }

    public String getImage() {
        return image;
    }
}
