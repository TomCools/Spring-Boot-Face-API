package com.infosupport.ap.exercise.resources;

import com.infosupport.ap.exercise.helpers.ImageDecoder;
import com.infosupport.ap.exercise.models.Presence;
import com.infosupport.ap.exercise.models.RegistrationImage;
import com.infosupport.ap.exercise.repositories.PresenceRepository;
import com.infosupport.ap.exercise.services.CognitiveServices;
import com.infosupport.ap.exercise.services.responses.PersonDetails;
import com.infosupport.ap.exercise.services.responses.partial.IdentificationCandidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@RestController
public class ImageResource {

    PresenceRepository presenceRepository;

    CognitiveServices faceAPI;

    ImageDecoder imageDecoder;

    @Autowired
    public ImageResource(PresenceRepository presenceRepository, CognitiveServices faceAPI, ImageDecoder imageDecoder) {
        this.presenceRepository = presenceRepository;
        this.faceAPI = faceAPI;
        this.imageDecoder = imageDecoder;
    }

    @RequestMapping(value = "/api/image", method = RequestMethod.POST, consumes = "application/json")
    public Presence uploadFile(@RequestBody RegistrationImage body) {
        //Decoding
        byte[] imageBytes = imageDecoder.decodeImage(body.getImage());

        // Detect a Person
        var person = detectPerson(imageBytes);

        //create presence and repository
        Presence presence = new Presence(body.getClassId(), person.getName(), new Date());
        presenceRepository.save(presence);

        return presence;
    }

    private PersonDetails detectPerson(byte[] image) {
        //Detecting person in image
        var detectFace = faceAPI.detect(image);

        var identification = faceAPI.identify(Collections.singletonList(detectFace.getFaceId()));
        if(identification.getCandidates().isEmpty()) {
            //Could be improved with custom exception instead of the generic one!
            throw new RuntimeException("No Candidate found for the supplied face.");
        }

        // Select identification with highest confidence
        var candidate = Collections.max(identification.getCandidates(), Comparator.comparing(IdentificationCandidate::getConfidence));

        return faceAPI.getPerson(candidate.getPersonId());
    }


}
