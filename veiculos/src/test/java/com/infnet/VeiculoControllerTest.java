package com.infnet;

import com.infnet.controller.VeiculoController;
import com.infnet.model.VeiculoModel;
import com.infnet.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStatus() {
        ResponseEntity<String> response = veiculoController.getStatus();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Serviço está ativo", response.getBody());
    }

    @Test
    public void testListarTodos() {
        VeiculoModel veiculo1 = new VeiculoModel();
        VeiculoModel veiculo2 = new VeiculoModel();
        when(veiculoRepository.findAll()).thenReturn(Flux.just(veiculo1, veiculo2));

        Flux<VeiculoModel> result = veiculoController.listarTodos();

        StepVerifier.create(result)
                .expectNext(veiculo1)
                .expectNext(veiculo2)
                .verifyComplete();
    }

    @Test
    public void testCriarVeiculo() {
        VeiculoModel veiculo = new VeiculoModel();
        when(veiculoRepository.save(any(VeiculoModel.class))).thenReturn(Mono.just(veiculo));

        Mono<VeiculoModel> result = veiculoController.criarVeiculo(veiculo);

        StepVerifier.create(result)
                .expectNext(veiculo)
                .verifyComplete();
    }

    @Test
    public void testBuscarPorId() {
        VeiculoModel veiculo = new VeiculoModel();
        when(veiculoRepository.findById(anyLong())).thenReturn(Mono.just(veiculo));

        Mono<VeiculoModel> result = veiculoController.buscarPorId(1L);

        StepVerifier.create(result)
                .expectNext(veiculo)
                .verifyComplete();
    }

    @Test
    public void testAtualizarVeiculo() {
        VeiculoModel veiculo = new VeiculoModel();
        VeiculoModel veiculoAtualizado = new VeiculoModel();
        veiculoAtualizado.setModelo("Novo Modelo");
        veiculoAtualizado.setMarca("Nova Marca");

        when(veiculoRepository.findById(anyLong())).thenReturn(Mono.just(veiculo));
        when(veiculoRepository.save(any(VeiculoModel.class))).thenReturn(Mono.just(veiculoAtualizado));

        Mono<VeiculoModel> result = veiculoController.atualizarVeiculo(1L, veiculoAtualizado);

        StepVerifier.create(result)
                .expectNextMatches(v -> v.getModelo().equals("Novo Modelo") && v.getMarca().equals("Nova Marca"))
                .verifyComplete();
    }

    @Test
    public void testDeletarVeiculo() {
        when(veiculoRepository.deleteById(anyLong())).thenReturn(Mono.empty());

        Mono<Void> result = veiculoController.deletarVeiculo(1L);

        StepVerifier.create(result)
                .verifyComplete();
    }
}
