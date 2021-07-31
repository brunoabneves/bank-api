package com.bruno.domain.service;

import java.math.BigDecimal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.ContaRepository;
import com.bruno.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroContaService {
	
	private ContaRepository contaRepository;
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Conta salvar(Conta conta) {
		
		//Usuario usuario = usuarioService.buscar(conta.getUsuario().getId());
		
		Conta contaExistente = contaRepository.findByNumero(conta.getNumero());
		
		if (contaExistente != null) {
			throw new NegocioException("Já existe uma conta com o número informado.");
		}
		
		if((conta.getSaldo().compareTo(BigDecimal.ZERO)) < 0) {
			throw new NegocioException("Saldo deve ser maior ou igual a zero.");
		}
		
		return contaRepository.save(conta);
	}
	
	public Usuario getUsuarioLogado() {
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		String username; 
		if (usuarioLogado instanceof UserDetails ) { 
			username= ( (UserDetails)usuarioLogado).getUsername(); 
		} else { 
			username = usuarioLogado .toString(); 
		}
		
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		return usuario;
	}
 
}
