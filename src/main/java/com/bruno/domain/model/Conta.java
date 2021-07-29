package com.bruno.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Conta {
	
	@EqualsAndHashCode.Include
	@Id
	@NotBlank
	@Size(min = 6, max = 6)
	private String numero;
	
	@ManyToOne
	private Usuario usuario;
	
	private BigDecimal saldo;
}
