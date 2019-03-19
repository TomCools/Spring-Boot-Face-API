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

@Service
public class CognitiveServices {

    private final static String BASEURL = "https://westus.api.cognitive.microsoft.com/face/v1.0/";
    private final static String KEY_HEADER = "Ocp-Apim-Subscription-Key";
    private final static String KEY = "6ff5ae598540491583a5ae9f11029697";
    private final static String PERSON_GROUP = "ap-java-spring-boot-one";

    @Autowired
    RestTemplate restTemplate;

    public DetectFaces detect(byte[] encodedImage) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(KEY_HEADER, KEY);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(encodedImage,requestHeaders);

        //IF return type is an Array, you need to wrap the return class like this :(
        ParameterizedTypeReference<List<DetectFaces>> type = new ParameterizedTypeReference<List<DetectFaces>>() {
        };

        ResponseEntity<List<DetectFaces>> response = restTemplate.exchange(BASEURL+"detect", HttpMethod.POST, requestEntity, type);
        System.out.println(response);
        return response.getBody().stream().findFirst().orElseThrow(() -> new RuntimeException("Multiple faces are not supported"));
    }

    //URL: https://westus.api.cognitive.microsoft.com/face/v1.0/identify
    public Identification identify(List<String> faceIds) {
        IdentifyFacesRequest request = new IdentifyFacesRequest(PERSON_GROUP, faceIds);

       //TODO Task 3B:
        return new Identification();
    }

    //URL https://westus.api.cognitive.microsoft.com/face/v1.0/persongroups/{personGroupId}/persons/{personId}
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
