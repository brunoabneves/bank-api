package com.bruno.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Conta;
import com.bruno.domain.repository.ContaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroContaService {
	
	private ContaRepository contaRepository;
	
	@Transactional
	public Conta salvar(Conta conta) {
		
		Conta contaExistente = contaRepository.findByNumero(conta.getNumero());
		
		if (contaExistente != null) {
			throw new NegocioException("Já existe uma conta com o número informado.");
		}
		
		if((conta.getSaldo().compareTo(BigDecimal.ZERO)) < 0) {
			throw new NegocioException("Saldo deve ser maior ou igual a zero.");
		}
		
		return contaRepository.save(conta);
	}
 

}
