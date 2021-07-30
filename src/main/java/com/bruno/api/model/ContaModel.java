package com.bruno.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaModel {
	
	private String numero;
	private UsuarioModel usuarioModel;
	private BigDecimal saldo;
}
