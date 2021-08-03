package com.bruno.controller;

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

import com.bruno.api.assembler.ContaAssembler;
import com.bruno.api.assembler.ContaSaldoAssembler;
import com.bruno.api.assembler.TransacaoAssembler;
import com.bruno.api.assembler.UsuarioAssembler;
import com.bruno.api.controller.ContaController;
import com.bruno.api.model.ContaModel;
import com.bruno.api.model.ContaSaldoModel;
import com.bruno.api.model.input.ContaSaldoInput;
import com.bruno.domain.model.Conta;
import com.bruno.domain.service.CadastroContaService;
import com.bruno.domain.service.TransacaoService;
import com.bruno.domain.service.UsuarioService;
import com.bruno.util.ContaCreator;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o controlador de contas")
public class ContaControllerTest {

	@InjectMocks
	private ContaController contaController;
	
	@Mock
	private CadastroContaService cadastroContaService;
	
	@Mock
	private ContaAssembler contaAssembler;
	@Mock
	private UsuarioService usuarioService;
	@Mock
	private UsuarioAssembler usuarioAssembler;
	@Mock
	private TransacaoService transacaoService;
	@Mock
	private TransacaoAssembler transacaoAssembler;
	@Mock
	private ContaSaldoAssembler contaSaldoAssembler;
	
	@BeforeEach
	void setUp() {
		BDDMockito.when(contaAssembler.toModel(cadastroContaService.salvar(ArgumentMatchers.any(Conta.class))))
			.thenReturn(ContaCreator.criaContaModel());
		
		BDDMockito.when(contaSaldoAssembler.toModel(transacaoService.contaExisteParaUsuario(ArgumentMatchers.anyString())))
			.thenReturn(ContaCreator.criaContaSaldoModel());
	}
	
	@Test
    @DisplayName("cadastrar retorna uma conta quando bem sucedido")
    void cadastrar_RetornaConta_QuandoSucesso(){
		
        ContaModel conta = contaController.cadastrar(ContaCreator.criaContaInput());

        ContaModel contaEsperada = ContaCreator.criaContaModel();
        
        Assertions.assertThat(conta).isNotNull();
        Assertions.assertThat(conta.getNumero()).isEqualTo(contaEsperada.getNumero());
        Assertions.assertThat(conta.getSaldo()).isEqualTo(contaEsperada.getSaldo());
    }
	
	@Test
    @DisplayName("exibir saldo retorna uma conta quando bem sucedido")
	void exibirSaldo_RetornaConta_QuandoSucesso() {
		
		ContaSaldoModel conta = contaController.exibirSaldo(ContaCreator.criaContaSaldoInput());
		ContaSaldoModel contaEsperada = ContaCreator.criaContaSaldoModel();
		
		Assertions.assertThat(conta).isNotNull();
        Assertions.assertThat(conta.getNumero()).isEqualTo(contaEsperada.getNumero());
        Assertions.assertThat(conta.getSaldo()).isEqualTo(contaEsperada.getSaldo());
	}
	
}
