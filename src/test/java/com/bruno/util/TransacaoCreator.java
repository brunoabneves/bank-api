package com.bruno.util;

import java.math.BigDecimal;

import com.bruno.api.model.TransacaoModel;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Transacao;

public class TransacaoCreator {

	public static Transacao criaTransacao() {
		Transacao transacao = new Transacao();
		
		Conta contaOrigem = ContaCreator.criaConta(UsuarioCreator.criaUsuarioValido());
		Conta contaDestino = ContaCreator.criaConta2(UsuarioCreator.criaUsuarioValido()); 
		
		transacao.setContaDestino(contaDestino.getNumero());
		transacao.setContaOrigem(contaOrigem.getNumero());
		transacao.setValor(new BigDecimal("1000"));
		transacao.setUsuario(contaOrigem.getUsuario());
		
		return transacao;
	}
	
	public static TransacaoModel criaTransacaoModel() {
		TransacaoModel transacao = new TransacaoModel();
		
		transacao.setContaDestino("4321-5");
		transacao.setContaOrigem("1111-1");
		transacao.setValor(new BigDecimal("1000"));
		transacao.setUsuario(UsuarioCreator.criaUsuarioModelValido());
		
		return transacao;
	}
}
