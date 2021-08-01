package com.bruno.util;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;

public class ContaCreator {

	@Test
	public static Conta criaConta(Usuario usuario) { 
		
		Conta conta = new Conta();

		//conta.setId(1L);
		conta.setNumero("1234-5");
		conta.setUsuario(usuario);
		conta.setSaldo(new BigDecimal("1000"));
		
		return conta;
	}
}
