package com.infosupport.ap.exercise.services.responses.partial;

public class IdentificationCandidate {
    private String personId;
    private double confidence;

    public IdentificationCandidate() {
    }

    public IdentificationCandidate(String personId, double confidence) {
        this.personId = personId;
        this.confidence = confidence;
    }

    public String getPersonId() {
        return personId;
    }

    public double getConfidence() {
        return confidence;
    }
}
