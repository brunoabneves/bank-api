package com.bruno.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaSaldoInput {

	@NotBlank
	@Size(min = 6, max = 6)
	private String numero;
}
