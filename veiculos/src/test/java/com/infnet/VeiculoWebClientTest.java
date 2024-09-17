package com.infnet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.infnet.service.VeiculoWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Testcontainers
@SpringBootTest
public class VeiculoWebClientTest {

    @Container
    public static GenericContainer<?> mockServer = new GenericContainer<>("mockserver/mockserver:latest")
            .withExposedPorts(1080);
            
    private WebClient webClient;
    private static VeiculoWebClient veiculoWebClient;

    @BeforeAll
    public static void setUpBeforeAll() {
        String baseUrl = "http://" + mockServer.getHost() + ":" + mockServer.getMappedPort(1080);
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        veiculoWebClient = new VeiculoWebClient(webClient);
    }
    
    @BeforeEach
    public void setUpBeforeEach() {
        webClient = Mockito.mock(WebClient.class);
        veiculoWebClient = new VeiculoWebClient(webClient);
    }

    @Test
    public void testGetVeiculos() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("http://localhost:8090/veiculos")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(String.class)).thenReturn(Flux.just("Veiculo1", "Veiculo2"));

        veiculoWebClient.getVeiculos();

        verify(webClient).get();
        verify(requestHeadersUriSpec).uri("http://localhost:8090/veiculos");
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToFlux(String.class);
    }

    @Test
    public void testGetVeiculoById() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("http://localhost:8090/veiculos/{id}"), any(Long.class)))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Veiculo"));

        veiculoWebClient.getVeiculoById(1L);

        verify(webClient).get();
        verify(requestHeadersUriSpec).uri("http://localhost:8090/veiculos/{id}", 1L);
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
    }

    @Test
    public void testPostVeiculo() {
        WebClient.RequestBodyUriSpec requestBodyUriSpec = Mockito.mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestBodySpec requestBodySpec = Mockito.mock(WebClient.RequestBodySpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class);

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri("http://localhost:8090/veiculos")).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue("Veiculo")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Veiculo"));

        veiculoWebClient.postVeiculo();

        verify(webClient).post();
        verify(requestBodyUriSpec).uri("http://localhost:8090/veiculos");
        verify(requestBodySpec).bodyValue("Veiculo");
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
    }

    @Test
    public void testPutVeiculo() {
        WebClient.RequestBodyUriSpec requestBodyUriSpec = Mockito.mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestBodySpec requestBodySpec = Mockito.mock(WebClient.RequestBodySpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class);

        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(eq("http://localhost:8090/veiculos/{id}"), any(Long.class)))
                .thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue("Veiculo")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Veiculo"));

        veiculoWebClient.putVeiculo(1L);

        verify(webClient).put();
        verify(requestBodyUriSpec).uri("http://localhost:8090/veiculos/{id}", 1L);
        verify(requestBodySpec).bodyValue("Veiculo");
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
    }

    @Test
    public void testDeleteVeiculo() {
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class);

        when(webClient.delete()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("http://localhost:8090/veiculos/{id}"), any(Long.class)))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Veiculo"));

        veiculoWebClient.deleteVeiculo(1L);

        verify(webClient).delete();
        verify(requestHeadersUriSpec).uri("http://localhost:8090/veiculos/{id}", 1L);
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
    }
}
