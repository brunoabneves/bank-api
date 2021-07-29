package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.service.UsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsuarioController {

	private UsuarioService usuarioService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public List<Usuario> listar(){
		
		return usuarioService.listar();
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrar(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
}
