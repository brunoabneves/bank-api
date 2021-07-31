package com.bruno.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transacao {
	
	private Usuario usuario;
	
	@NotBlank
	private String contaOrigem;
	
	@NotBlank
	private String contaDestino;
	
	@NotBlank
	private BigDecimal valor;
}
