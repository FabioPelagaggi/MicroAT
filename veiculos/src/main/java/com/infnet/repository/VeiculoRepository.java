package com.infnet.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.infnet.model.VeiculoModel;

public interface VeiculoRepository extends R2dbcRepository<VeiculoModel, Long> {}