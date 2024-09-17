package com.infnet.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.model.FactModel;

import reactor.core.publisher.Mono;

@Service
public class CatFactApiService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    public Mono<String> requestCatFact(String url) {
        return webClient.get()
                .uri("https://catfact.ninja/fact")
                .retrieve()
                .bodyToMono(String.class)
                .map(this::processData);
    }

    private String processData(String data) {
        try {
            FactModel fact = objectMapper.readValue(data, FactModel.class);
            return fact.getFact().toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing data";
        }
    }
}
