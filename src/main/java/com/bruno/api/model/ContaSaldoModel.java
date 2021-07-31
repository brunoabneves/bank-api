package com.bruno.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaSaldoModel {

	private String numero;
	private BigDecimal saldo;
}
