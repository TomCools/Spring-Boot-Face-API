package com.infosupport.ap.exercise.services.requests;

public class PersonDetailsRequest {
    private String personId;
    private String personGroupId;

    public PersonDetailsRequest(String personId, String personGroupId) {
        this.personId = personId;
        this.personGroupId = personGroupId;
    }

    public String getPersonId() {
        return personId;
    }

    public String getPersonGroupId() {
        return personGroupId;
    }
}
