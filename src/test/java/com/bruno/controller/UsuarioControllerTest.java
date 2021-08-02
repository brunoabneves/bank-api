package com.bruno.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bruno.api.assembler.UsuarioAssembler;
import com.bruno.api.controller.UsuarioController;
import com.bruno.api.model.UsuarioModel;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.service.UsuarioService;
import com.bruno.util.UsuarioCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o controlador de usuários")
public class UsuarioControllerTest {

	@InjectMocks
	private UsuarioController usuarioController;
	
	@Mock
	private UsuarioService usuarioService; 
	
	@Mock
	private UsuarioAssembler usuarioAssembler;
	
	@BeforeEach
	void setUp() {
		BDDMockito.when(usuarioAssembler.toCollectionModel(usuarioService.listarTodos()))
			.thenReturn(List.of(UsuarioCreator.criaUsuarioModelValido()));
		
		BDDMockito.when(usuarioAssembler.toModel(usuarioService.salvar(ArgumentMatchers.any(Usuario.class))))
        .thenReturn(UsuarioCreator.criaUsuarioModelValido());
	}
	
	@Test
	@DisplayName("listar retorna uma lista de usuarios quando bem sucedido")
	void listar_RetornaListaUsuarios_QuandoSucesso() {

		UsuarioModel usuario = UsuarioCreator.criaUsuarioModelValido();
		String nome = usuario.getNome();
		
		List<UsuarioModel> usuarios = usuarioController.listar();
		
		Assertions.assertThat(usuarios)
        	.isNotNull()
        	.isNotEmpty()
        	.hasSize(1);
		
		Assertions.assertThat(usuarios.get(0).getNome()).isEqualTo(nome);
	}
	
	@Test
    @DisplayName("cadastrar retorna usuário quando bem sucedido")
    void cadastrar_RetornaUsuario_QuandoSucesso(){

        UsuarioModel usuario = usuarioController.cadastrar(UsuarioCreator.criaUsuarioInput());

        UsuarioModel usuarioEsperado = UsuarioCreator.criaUsuarioModelValido();
        
        Assertions.assertThat(usuario).isNotNull();
        Assertions.assertThat(usuario.getId()).isEqualTo(usuarioEsperado.getId());
        Assertions.assertThat(usuario.getNome()).isEqualTo(usuarioEsperado.getNome());
        Assertions.assertThat(usuario.getEmail()).isEqualTo(usuarioEsperado.getEmail());
    }
}
