package com.infnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.model.VeiculoModel;
import com.infnet.repository.VeiculoRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Serviço está ativo");
    }

    @GetMapping("/listarTodos")
    public Flux<VeiculoModel> listarTodos() {
        return veiculoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VeiculoModel> criarVeiculo(@RequestBody VeiculoModel veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VeiculoModel> buscarPorId(@PathVariable Long id) {
        return veiculoRepository.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VeiculoModel> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoModel veiculoAtualizado) {
        return veiculoRepository.findById(id)
                .flatMap(veiculo -> {
                    veiculo.setModelo(veiculoAtualizado.getModelo());
                    veiculo.setMarca(veiculoAtualizado.getMarca());
                    return veiculoRepository.save(veiculo);
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletarVeiculo(@PathVariable Long id) {
        return veiculoRepository.deleteById(id);
    }
}
