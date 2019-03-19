package com.infosupport.ap.exercise.services.requests;

import java.util.List;

public class IdentifyFacesRequest {
    private String personGroupId;
    private List<String> faceIds;

    public IdentifyFacesRequest(String personGroupId, List<String> faceIds) {
        this.personGroupId = personGroupId;
        this.faceIds = faceIds;
    }

    public String getPersonGroupId() {
        return personGroupId;
    }

    public List<String> getFaceIds() {
        return faceIds;
    }
}
