package com.word_api.clients;

import com.word_api.config.WordsApiConfig;
import com.word_api.domains.WordData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class WordsApiClient {

    private final RestTemplate restTemplate;
    private final WordsApiConfig config;

    public WordData getSpecificWord(String word){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", config.getRapidApiKey());
        headers.set("x-rapidapi-host", config.getRapidApiHost());
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<WordData> responseObj = restTemplate.exchange(
                config.getRapidApiBasicEndpoint() + word,
                HttpMethod.GET, entity, WordData.class);
        log.info("Received request, searched word: "+word);
        try {
            return responseObj.getBody();
        } catch (Exception e) {
             e.getCause().getMessage();
            return new WordData();
        }
    }
}
