package com.infosupport.ap.exercise.services;

import com.infosupport.ap.exercise.services.interceptors.HeaderRequestInterceptor;
import com.infosupport.ap.exercise.services.requests.IdentifyFacesRequest;
import com.infosupport.ap.exercise.services.responses.DetectFaces;
import com.infosupport.ap.exercise.services.responses.PersonDetails;
import com.infosupport.ap.exercise.services.responses.partial.Identification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

        //IF return type is an Array, you need to wrap the return class like this :(
        ParameterizedTypeReference<List<DetectFaces>> type = new ParameterizedTypeReference<List<DetectFaces>>() {
        };

        ResponseEntity<List<DetectFaces>> response = restTemplate.exchange(format("%sdetect", baseUrl), HttpMethod.POST, requestEntity, type);
        System.out.println(response);
        return response.getBody().stream().findFirst().orElseThrow(() -> new RuntimeException("Multiple faces are not supported"));
    }

    public Identification identify(List<String> faceIds) {
        IdentifyFacesRequest request = new IdentifyFacesRequest(personGroup, faceIds);

        HttpEntity<?> requestEntity = new HttpEntity<Object>(request);

        ParameterizedTypeReference<List<Identification>> type = new ParameterizedTypeReference<List<Identification>>() {
        };

        ResponseEntity<List<Identification>> response = restTemplate.exchange(format("%sidentify", baseUrl), HttpMethod.POST, requestEntity, type);
        return response.getBody().stream().findFirst().orElseThrow(() -> new RuntimeException("Multiple faces are not supported"));
    }

    // https://westus.api.cognitive.microsoft.com/face/v1.0/persongroups/{personGroupId}/persons/{personId}
    public PersonDetails getPerson(String personId) {
        String url = format("%spersongroups/%s/persons/%s", baseUrl, personGroup, personId);
        ResponseEntity<PersonDetails> entity = restTemplate.getForEntity(url, PersonDetails.class);
        return entity.getBody();
    }

    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(singletonList(new HeaderRequestInterceptor(KEY_HEADER, apiKey)));
        return template;
    }
}
