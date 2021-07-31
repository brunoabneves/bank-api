package com.bruno.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoModel {

	private UsuarioModel usuario;
	private String contaOrigem;
	private String contaDestino;
	private BigDecimal valor;
}
