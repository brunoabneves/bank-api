package com.bruno.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bruno.domain.model.Usuario;
import com.bruno.util.UsuarioCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes para o controlador de usuários")
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("listar retorna um status Ok quando bem sucedido")
	public void listar_RetornaStatusOk_QuandoSucesso() throws Exception {
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("cadastrar retorna um status Created quando bem sucedido")
	public void cadastrar_RetornaStatusCreated() throws Exception {
		
		Usuario usuario = UsuarioCreator.criaUsuarioValido();
		
		mockMvc.perform(post("/users")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(usuario)))
				.andExpect(status().isCreated());
	
	}
}
