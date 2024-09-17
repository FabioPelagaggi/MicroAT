package com.infnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.service.CatFactApiService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CatFactController {

    @Value("${catfact.url}")
    private String catFactUrl;

    @Autowired
    private CatFactApiService apiService;

    @GetMapping("/catfact")
    public Mono<String> getCatFact() {
        return apiService.requestCatFact(catFactUrl);
    }
    
}
