package com.word_api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WordsApiConfig {

    @Value("${rapidApiKey}")
    private String rapidApiKey;
    @Value("${rapidApiHost}")
    private String rapidApiHost;
    @Value("${rapidApiEndpoint}")
    private String rapidApiEndpoint;
    @Value("${rapidApiBasicEndpoint}")
    private String rapidApiBasicEndpoint;

}
