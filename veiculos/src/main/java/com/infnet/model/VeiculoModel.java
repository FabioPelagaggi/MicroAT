package com.infnet.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VeiculoModel {

	@Id
    private Long id;
    private String marca;
    private String modelo;
    private String ano;
    private String placa;
}
