package com.bruno.repository;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.util.UsuarioCreator;

@DataJpaTest
@DisplayName("Testes para o repositório de usuarios")
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	@DisplayName("Salva um usuário quando bem sucedido")
	void save_PersisteUsuario_QuandoSucesso() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuario();
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(usuarioASerSalvo.getId());
	}
	
	@Test
	@DisplayName("Retrona um usuário quando bem sucedido")
	void findByEmail_retornaUsuario_QuandoSucesso() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuario();
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		String email = usuarioSalvo.getEmail();
		
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		
		Assertions.assertThat(usuario).isNotNull();
		Assertions.assertThat(usuario.getId()).isNotNull();
		Assertions.assertThat(usuario.getEmail()).isEqualTo(usuarioSalvo.getEmail());
	}
	
	@Test
	@DisplayName("Retrona Null quando usuário não é encontrado ")
	void findByEmail_retornaNull_QuandoUsuarioNaoEncontrado() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuario();
		this.usuarioRepository.save(usuarioASerSalvo);
		
		Usuario usuario = this.usuarioRepository.findByEmail("sasda@email.com");
		
		Assertions.assertThat(usuario).isNull();
	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando nome está em branco")
	void save_ThrowConstraintViolationException_QuandoNomeEmBranco() {
		Usuario usuario = UsuarioCreator.criaUsuario();
		usuario.setNome("");
		
		Assertions.assertThatThrownBy(() -> this.usuarioRepository.save(usuario))
				.isInstanceOf(ConstraintViolationException.class);

	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando ultrapassa limite do size do nome")
	void save_ThrowConstraintViolationException_QuandoSizeNomeUltrapassaLimite() {
		Usuario usuario = UsuarioCreator.criaUsuario();
		usuario.setNome("aasddddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaaaaa");
		
		Assertions.assertThatThrownBy(() -> this.usuarioRepository.save(usuario))
				.isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando email está em branco")
	void save_ThrowConstraintViolationException_QuandoEmailEmBranco() {
		Usuario usuario = UsuarioCreator.criaUsuario();
		usuario.setEmail("");
		
		Assertions.assertThatThrownBy(() -> this.usuarioRepository.save(usuario))
				.isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando formato do email está errado")
	void save_ThrowConstraintViolationException_QuandoFormatoEmailErrado() {
		Usuario usuario = UsuarioCreator.criaUsuario();
		usuario.setEmail("felagundemail.com");
		
		Assertions.assertThatThrownBy(() -> this.usuarioRepository.save(usuario))
				.isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando senha está em branco")
	void save_ThrowConstraintViolationException_QuandoSenhaEmBranco() {
		Usuario usuario = UsuarioCreator.criaUsuario();
		usuario.setSenha("");
		
		Assertions.assertThatThrownBy(() -> this.usuarioRepository.save(usuario))
				.isInstanceOf(ConstraintViolationException.class);
	}
}
