package com.infosupport.ap.exercise.resources;

import com.infosupport.ap.exercise.helpers.ImageDecoder;
import com.infosupport.ap.exercise.models.Presence;
import com.infosupport.ap.exercise.models.RegistrationImage;
import com.infosupport.ap.exercise.services.CognitiveServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageResource {

    CognitiveServices faceAPI;

    ImageDecoder imageDecoder;

    @Autowired
    public ImageResource(CognitiveServices faceAPI, ImageDecoder imageDecoder) {
        this.faceAPI = faceAPI;
        this.imageDecoder = imageDecoder;
    }

    @RequestMapping(value = "/api/image", method = RequestMethod.POST, consumes = "application/json")
    public Presence uploadFile(@RequestBody RegistrationImage body) throws IOException {
        //Decoding
        byte[] imageBytes = imageDecoder.decodeImage(body.getImage());

        //TODO Task 3B - Detecting person in image

        //END - TODO TASK 3B

        //TODO Task 4: create presence and repository, save and return
        return new Presence();
    }


}
