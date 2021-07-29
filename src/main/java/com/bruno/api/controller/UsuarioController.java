package com.bruno.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Usuario;

@RestController
public class UsuarioController {
	
	@GetMapping("/users")
	public List<Usuario> listar(){
		var usuario1 = new Usuario();
		usuario1.setId(1L);
		usuario1.setNome("Elwe Singolo");
		usuario1.setEmail("capacinzenta@email.com");
		usuario1.setSenha("thingol");
		
		
		var usuario2 = new Usuario();
		usuario2.setId(2L);
		usuario2.setNome("Feanor");
		usuario2.setEmail("feanor@email.com");
		usuario2.setSenha("silmarill");
		
		return Arrays.asList(usuario1, usuario2);
	}
	
}
