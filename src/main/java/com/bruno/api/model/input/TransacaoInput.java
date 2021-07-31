package com.bruno.api.model.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoInput {

	private String contaOrigem;
	
	private String contaDestino;
	
	private BigDecimal valor;
}
