package com.infosupport.ap.exercise.services.responses;

import com.infosupport.ap.exercise.services.responses.partial.IdentificationCandidate;

import java.util.List;

public class Identification {
    private String faceId;
    private List<IdentificationCandidate> candidates;

    public Identification() {
    }

    public Identification(String faceId, List<IdentificationCandidate> candidates) {
        this.faceId = faceId;
        this.candidates = candidates;
    }

    public String getFaceId() {
        return faceId;
    }

    public List<IdentificationCandidate> getCandidates() {
        return candidates;
    }
}
