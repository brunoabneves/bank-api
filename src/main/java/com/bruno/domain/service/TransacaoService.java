package com.bruno.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.api.assembler.ContaAssembler;
import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.ContaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransacaoService {
	
	private CadastroContaService cadastroContaService;
	private ContaRepository contaRepository;
	private ContaAssembler contaAssembler;
	
	@Transactional
	public Conta contaExisteParaUsuario(String contaOrigem) {
		
		Usuario usuario = cadastroContaService.getUsuarioLogado();
		
		Conta contaExiste = contaRepository.findByUsuarioIdAndNumero(usuario.getId(), contaOrigem);
		
		//Stream<Conta> contaExiste = contas.stream().filter(conta -> conta.getNumero().equals(contaOrigem));
		
		if(contaExiste == null) {
			throw new NegocioException("Conta de origem não encontrada para o usuário informado.");
		}
		
		return contaExiste;
		
	}
	
	public void transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		
		//força uma atualização. Não cria uma nova conta
		contaOrigem.setId(contaOrigem.getId());
		contaDestino.setId(contaDestino.getId());
				
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
	}

}
