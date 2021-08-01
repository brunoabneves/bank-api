package com.bruno.util;

import org.junit.jupiter.api.Test;

import com.bruno.domain.model.Usuario;

public class UsuarioCreator {

	@Test
	public static Usuario criaUsuario() {
		Usuario usuario = new Usuario();
		
		//usuario.setId(1L);
		usuario.setNome("Finrod Felagund");
		usuario.setEmail("felagund@email.com");
		usuario.setSenha("melhorelfo");
		
		return usuario;
	}
	
	@Test
	public static Usuario criaUsuarioValido() {
		Usuario usuario = new Usuario();
		
		usuario.setId(1L);
		usuario.setNome("Finrod Felagund");
		usuario.setEmail("felagund@email.com");
		usuario.setSenha("melhorelfo");
		
		return usuario;
	}
}
