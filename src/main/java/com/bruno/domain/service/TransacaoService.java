package com.bruno.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.ContaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransacaoService {

	private ContaRepository contaRepository;
	private UsuarioService usuarioService;
	
	@Transactional
	public Conta contaExisteParaUsuario(String contaOrigem) {
		
		Conta contaExiste = buscaPorUsuarioLogadoAndConta(contaOrigem);
		
		//Stream<Conta> contaExiste = contas.stream().filter(conta -> conta.getNumero().equals(contaOrigem));
		
		if(contaExiste == null) {
			throw new NegocioException("Conta de origem não encontrada para o usuário informado.");
		}
		
		return contaExiste;
		
	}
	
	public Conta encontraContaDestino(String contaDestino) {
		Conta contaExiste = contaRepository.findByNumero(contaDestino);
		
		if(contaExiste == null) {
			throw new NegocioException("Conta destino não encontrada.");
		}
		
		return contaExiste;
	}
	
	public void transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		
		testaValor(valor);
		testaSaldoConta(contaOrigem, valor);
		
		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		
		//força uma atualização. Não cria uma nova conta
		contaOrigem.setId(contaOrigem.getId());
		contaDestino.setId(contaDestino.getId());
				
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
	}
	
	public void testaSaldoConta(Conta conta, BigDecimal valor) {
		
		if(valor.compareTo(conta.getSaldo()) == 1) {
			throw new NegocioException("Saldo insuficiente na conta de origem.");
		}
	}
	
	public void testaValor(BigDecimal valor) {
		if(valor.compareTo(BigDecimal.ZERO) < 0) {
			throw new NegocioException("Valor deve ser maior que zero.");
		}
	}
	
	public Conta buscaPorUsuarioLogadoAndConta(String contaOrigem) {
		
		Usuario usuario = usuarioService.getUsuarioLogado();
		
		Conta conta = contaRepository.findByUsuarioIdAndNumero(usuario.getId(), contaOrigem);
		
		return conta;
		
	}

}
