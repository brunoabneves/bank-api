package com.bruno.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.ContaAssembler;
import com.bruno.api.assembler.ContaSaldoAssembler;
import com.bruno.api.model.ContaModel;
import com.bruno.api.model.ContaSaldoModel;
import com.bruno.api.model.input.ContaInput;
import com.bruno.api.model.input.ContaSaldoInput;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.service.CadastroContaService;
import com.bruno.domain.service.TransacaoService;
import com.bruno.domain.service.UsuarioService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class ContaController {

	private CadastroContaService cadastroContaService;
	private ContaAssembler contaAssembler;
	private TransacaoService transacaoService;
	private ContaSaldoAssembler contaSaldoAssembler;
	private UsuarioService usuarioService;
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token",
				required = true, dataType = "string", paramType = "header")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ContaModel cadastrar(@Valid @RequestBody ContaInput contaInput) {
		
		Usuario usuario = usuarioService.getUsuarioLogado();
		contaInput.setUsuario(usuario);
		
		Conta novaConta = contaAssembler.toEntity(contaInput);
		
		return contaAssembler.toModel(cadastroContaService.salvar(novaConta));
	}
	
	@GetMapping("/balance")
	@PreAuthorize("hasRole('USER')")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token",
				required = true, dataType = "string", paramType = "header")
	})
	public ContaSaldoModel exibirSaldo (@RequestBody ContaSaldoInput contaSaldoInput) {
		
		Conta conta = transacaoService.contaExisteParaUsuario(contaSaldoInput.getNumero());
		
		return contaSaldoAssembler.toModel(conta);
	}
	
}
