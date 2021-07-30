package com.bruno.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioExistente != null) {
			throw new NegocioException("Já existe um usuário cadastrado com este e-mail.");
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new NegocioException("Usuário não encontrado."));
	}
	
}
