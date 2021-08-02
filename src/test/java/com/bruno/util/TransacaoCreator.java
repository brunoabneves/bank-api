package com.bruno.util;

import java.math.BigDecimal;

import com.bruno.domain.model.Transacao;

public class TransacaoCreator {

	public static Transacao criaTransacao() {
		Transacao transacao = new Transacao();
		
		transacao.setContaDestino("4321-5");
		transacao.setContaOrigem("1111-1");
		transacao.setValor(new BigDecimal("1000"));
		transacao.setUsuario(UsuarioCreator.criaUsuarioValido());
		
		return transacao;
	}
}
