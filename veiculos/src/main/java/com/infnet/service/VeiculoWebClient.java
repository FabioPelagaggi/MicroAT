package com.infnet.service;

import org.springframework.web.reactive.function.client.WebClient;

public class VeiculoWebClient {

    private final WebClient webClient;

    public VeiculoWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public void getVeiculos() {
        webClient.get()
                .uri("http://localhost:8090/veiculos")
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(System.out::println);
    }

    public void getVeiculoById(Long id) {
        webClient.get()
                .uri("http://localhost:8090/veiculos/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    public void postVeiculo() {
        webClient.post()
                .uri("http://localhost:8090/veiculos")
                .bodyValue("Veiculo")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    public void putVeiculo(Long id) {
        webClient.put()
                .uri("http://localhost:8090/veiculos/{id}", id)
                .bodyValue("Veiculo")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    public void deleteVeiculo(Long id) {
        webClient.delete()
                .uri("http://localhost:8090/veiculos/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }
}
