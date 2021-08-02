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
	
	@Test
	public static Usuario criaUsuarioValido2() {
		Usuario usuario = new Usuario();
		
		usuario.setId(2L);
		usuario.setNome("Eren Yeager");
		usuario.setEmail("eren@email.com");
		usuario.setSenha("tatakae");
		
		return usuario;
	}
	
//	public static List<Usuario> CriaListaDeUsuarios() {
//		List<Usuario> lista = new ArrayList<>(); 
//		
//		Usuario usuario1 = new Usuario();
//	
//		usuario1.setId(1L);
//		usuario1.setNome("Finrod Felagund");
//		usuario1.setEmail("felagund@email.com");
//		usuario1.setSenha("melhorelfo");
//		
//		Usuario usuario2 = new Usuario();
//		
//		usuario2.setId(2L);
//		usuario2.setNome("Eren Yeager");
//		usuario2.setEmail("eren@email.com");
//		usuario2.setSenha("tatakae");
//		
//		lista.add(usuario1);
//		lista.add(usuario2);
//		
//		return lista;
//	}
}
