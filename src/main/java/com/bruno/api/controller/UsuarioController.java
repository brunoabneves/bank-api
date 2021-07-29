package com.bruno.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UsuarioController {
	
	
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/users")
	public List<Usuario> listar(){
		
		return usuarioRepository.findAll();
	}
	
}
