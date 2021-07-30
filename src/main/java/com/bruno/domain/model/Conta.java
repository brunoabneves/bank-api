package com.bruno.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.bruno.domain.ValidationGroups;

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
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.UsuarioId.class)
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	private BigDecimal saldo;
}
