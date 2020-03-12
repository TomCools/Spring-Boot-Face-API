package com.infosupport.ap.exercise.services;

import com.infosupport.ap.exercise.services.interceptors.HeaderRequestInterceptor;
import com.infosupport.ap.exercise.services.requests.IdentifyFacesRequest;
import com.infosupport.ap.exercise.services.responses.DetectFaces;
import com.infosupport.ap.exercise.services.responses.PersonDetails;
import com.infosupport.ap.exercise.services.responses.partial.Identification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;

@Service
public class CognitiveServices {

    private final static String KEY_HEADER = "Ocp-Apim-Subscription-Key";

    @Value("${cognitiveservice.key}")
    private String apiKey;
    @Value("${cognitiveservice.persongroup}")
    private String personGroup;
    @Value("${cognitiveservice.baseurl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate; //With Spring Test, you can change the Autowiring so it injects a "Fake" restTemplate

    public DetectFaces detect(byte[] encodedImage) {
        HttpEntity<?> requestEntity = new HttpEntity<Object>(encodedImage);

        ResponseEntity<DetectFaces[]> response = restTemplate.postForEntity(format("%sdetect", baseUrl), requestEntity, DetectFaces[].class);
        DetectFaces[] body = response.getBody();

        return determineResult(body);
    }

    public Identification identify(List<String> faceIds) {
        IdentifyFacesRequest request = new IdentifyFacesRequest(personGroup, faceIds);

        HttpEntity<?> requestEntity = new HttpEntity<Object>(request);

        ResponseEntity<Identification[]> response = restTemplate.postForEntity(format("%sidentify", baseUrl), requestEntity, Identification[].class);
        Identification[] body = response.getBody();

        return determineResult(body);
    }

    // https://westus.api.cognitive.microsoft.com/face/v1.0/persongroups/{personGroupId}/persons/{personId}
    public PersonDetails getPerson(String personId) {
        String url = format("%spersongroups/%s/persons/%s", baseUrl, personGroup, personId);
        ResponseEntity<PersonDetails> entity = restTemplate.getForEntity(url, PersonDetails.class);
        return entity.getBody();
    }

    private <T> T determineResult(T[] body) {
        if (body.length != 1) {
            throw new RuntimeException(String.format("Expected only 1 face, but found: %d", body.length));
        }
        return body[0];
    }

    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(singletonList(new HeaderRequestInterceptor(KEY_HEADER, apiKey)));
        return template;
    }
}
