package com.bruno.util;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.bruno.api.model.ContaModel;
import com.bruno.api.model.ContaSaldoModel;
import com.bruno.api.model.input.ContaInput;
import com.bruno.api.model.input.ContaSaldoInput;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;

public class ContaCreator {

	@Test
	public static Conta criaConta(Usuario usuario) { 
		
		Conta conta = new Conta();

		conta.setId(1L);
		conta.setNumero("1234-5");
		conta.setUsuario(usuario);
		conta.setSaldo(new BigDecimal("2000"));
		
		return conta;
	}
	
	@Test
	public static ContaModel criaContaModel() {
		ContaModel conta = new ContaModel();
		
		conta.setNumero("7777-7");;
		conta.setSaldo(new BigDecimal("1500"));
		conta.setUsuario(UsuarioCreator.criaUsuarioModelValido());
		
		return conta;
	}
	
	@Test
	public static ContaInput criaContaInput() {
		ContaInput conta = new ContaInput();
		
		conta.setNumero("7777-7");;
		conta.setSaldo(new BigDecimal("1500"));
		conta.setUsuario(UsuarioCreator.criaUsuarioValido());
		
		return conta;
	}

	public static Conta criaConta2(Usuario usuario) {
		Conta conta = new Conta();

		conta.setId(1L);
		conta.setNumero("4545-4");
		conta.setUsuario(usuario);
		conta.setSaldo(new BigDecimal("2000"));
		
		return conta;
	}

	public static ContaSaldoModel criaContaSaldoModel() {
		ContaSaldoModel conta = new ContaSaldoModel();
		
		conta.setNumero("7777-7");
		conta.setSaldo(new BigDecimal("1500"));
		
		return conta;
	}

	public static ContaSaldoInput criaContaSaldoInput() {
		
		ContaSaldoInput conta = new ContaSaldoInput();
		conta.setNumero("1111-1");
		
		return conta;
	}
}
