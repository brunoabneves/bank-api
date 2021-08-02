package com.bruno.util;

import org.junit.jupiter.api.Test;

import com.bruno.api.model.UsuarioModel;
import com.bruno.api.model.input.UsuarioInput;
import com.bruno.domain.model.Usuario;

public class UsuarioCreator {

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
	
	@Test
	public static UsuarioModel criaUsuarioModelValido() {
		UsuarioModel usuario = new UsuarioModel();
		
		usuario.setId(1L);
		usuario.setNome("Turim Turambar");
		usuario.setEmail("ruinadeglaurung@email.com");
		
		return usuario;
	}

	public static UsuarioInput criaUsuarioInput() {
		UsuarioInput usuario = new UsuarioInput();
		
		usuario.setNome("Turim Turambar");
		usuario.setEmail("ruinadeglaurung@email.com");
		usuario.setSenha("asdfghjk");
		
		return usuario;
	}
}
