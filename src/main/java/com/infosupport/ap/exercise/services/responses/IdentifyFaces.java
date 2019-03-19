package com.infosupport.ap.exercise.services.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infosupport.ap.exercise.services.responses.partial.Identification;

import java.util.List;

public class IdentifyFaces {
    @JsonProperty("")
    private List<Identification> identification;

    public IdentifyFaces() {
    }

    public IdentifyFaces(List<Identification> identification) {
        this.identification = identification;
    }

    public List<Identification> getIdentification() {
        return identification;
    }
}
