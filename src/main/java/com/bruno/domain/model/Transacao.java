package com.bruno.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transacao {
	
	@JsonIgnore
	private Usuario usuario;
	
	@NotBlank
	private String contaOrigem;
	
	@NotBlank
	private String contaDestino;
	
	@NotBlank
	private BigDecimal valor;
}
