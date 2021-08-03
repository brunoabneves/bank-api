package com.bruno.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bruno.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaInput {

	@NotBlank
	@Size(min = 6, max = 6)
	private String numero;
	
	@NotNull
	private BigDecimal saldo;
	
	@JsonIgnore
	private Usuario usuario;
	
}
