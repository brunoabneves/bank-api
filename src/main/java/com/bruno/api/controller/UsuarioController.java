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

import com.bruno.api.assembler.UsuarioAssembler;
import com.bruno.api.model.UsuarioModel;
import com.bruno.api.model.input.UsuarioInput;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.domain.service.UsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsuarioController {

	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepository;
	private UsuarioAssembler usuarioAssembler;
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public List<UsuarioModel> listar() {
		return usuarioAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel cadastrar (@Valid @RequestBody UsuarioInput usuarioInput) {
		Usuario novoUsuario = usuarioAssembler.toEntity(usuarioInput);
		return usuarioAssembler.toModel(usuarioService.salvar(novoUsuario));
	}
}
