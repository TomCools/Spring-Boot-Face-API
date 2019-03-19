package com.infosupport.ap.exercise.services.responses;

public class DetectFaces {
    private String faceId;

    public DetectFaces() {
    }

    public DetectFaces(String faceId) {
        this.faceId = faceId;
    }

    public String getFaceId() {
        return faceId;
    }
}
