package com.infnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infnet.model.VeiculoModel;
import com.infnet.repository.VeiculoRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class VeiculoService {

    @Autowired
    private final VeiculoRepository veiculoRepository;

    public Flux<VeiculoModel> findAll() {
        return veiculoRepository.findAll();
    }

    public Mono<VeiculoModel> findById(Long id) {
        return veiculoRepository.findById(id);
    }

    public Mono<VeiculoModel> save(VeiculoModel veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Mono<Void> deleteById(Long id) {
        return veiculoRepository.deleteById(id);
    }
    
}
