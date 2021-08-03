package com.bruno.util;

import java.math.BigDecimal;

import com.bruno.api.model.TransacaoModel;
import com.bruno.domain.model.Transacao;

public class TransacaoCreator {

	public static Transacao criaTransacao(String contaOrigem, String contaDestino) {
		Transacao transacao = new Transacao();
		
		transacao.setContaDestino(contaDestino);
		transacao.setContaOrigem(contaOrigem);
		transacao.setValor(new BigDecimal("1000"));
		transacao.setUsuario(UsuarioCreator.criaUsuarioValido());
		
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
