package com.infosupport.ap.exercise.services;

import com.infosupport.ap.exercise.services.interceptors.HeaderRequestInterceptor;
import com.infosupport.ap.exercise.services.requests.IdentifyFacesRequest;
import com.infosupport.ap.exercise.services.responses.DetectFaces;
import com.infosupport.ap.exercise.services.responses.Identification;
import com.infosupport.ap.exercise.services.responses.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Service
public class CognitiveServices {

    private final static String BASEURL = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/";
    private final static String KEY_HEADER = "Ocp-Apim-Subscription-Key";
    private final static String KEY = "0c78577bab714f53beec5121880c092f";
    private final static String PERSON_GROUP = "ap-spring-students";

    @Autowired
    RestTemplate restTemplate;

    public DetectFaces detect(byte[] encodedImage) {
        HttpEntity<?> requestEntity = new HttpEntity<Object>(encodedImage);

        ResponseEntity<DetectFaces[]> response = restTemplate.postForEntity(format("%sdetect", BASEURL), requestEntity, DetectFaces[].class);
        DetectFaces[] body = response.getBody();

        if(body == null || body.length != 1) {
            throw new RuntimeException("This exerices does not support multiple faces or no faces!");
        }
        return body[0]; //Just return the first, can be improved (a lot!) if you still have time left.
    }

    //URL: https://westeurope.api.cognitive.microsoft.com/face/v1.0/identify
    public Identification identify(List<String> faceIds) {
        IdentifyFacesRequest request = new IdentifyFacesRequest(PERSON_GROUP, faceIds);

       //TODO Task 3B:
        return new Identification();
    }

    //URL https://westeurope.api.cognitive.microsoft.com/face/v1.0/persongroups/{personGroupId}/persons/{personId}
    public PersonDetails getPerson(String personId) {
        return new PersonDetails();
    }

    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(new HeaderRequestInterceptor(KEY_HEADER, KEY)));
        return template;
    }
}
