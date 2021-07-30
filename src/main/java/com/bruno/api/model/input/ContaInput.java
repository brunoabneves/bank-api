package com.bruno.api.model.input;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaInput {

	@EqualsAndHashCode.Include
	@Id
	@NotBlank
	@Size(min = 6, max = 6)
	private String numero;
	
	@NotNull
	private BigDecimal saldo;
	
}
