package com.bruno.domain.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	public List<Usuario> listarTodos(){
		return usuarioRepository.findAll();
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
