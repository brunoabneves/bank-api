package com.bruno.domain.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transacao {
	
	private Usuario usuario;
	
	private String contaOrigem;
	
	private String contaDestino;
	
	private BigDecimal valor;
}
