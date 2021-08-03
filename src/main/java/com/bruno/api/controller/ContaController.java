package com.bruno.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.ContaAssembler;
import com.bruno.api.assembler.ContaSaldoAssembler;
import com.bruno.api.assembler.TransacaoAssembler;
import com.bruno.api.model.ContaModel;
import com.bruno.api.model.ContaSaldoModel;
import com.bruno.api.model.TransacaoModel;
import com.bruno.api.model.input.ContaInput;
import com.bruno.api.model.input.ContaSaldoInput;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Transacao;
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
	private TransacaoAssembler transacaoAssembler;
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
	
	@PutMapping("/transfer")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Bearer token",
				required = true, dataType = "string", paramType = "header")
	})
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<TransacaoModel> transacaoEntreContas(@RequestBody Transacao transacaoInput) {
		Usuario usuario = usuarioService.getUsuarioLogado();
		transacaoInput.setUsuario(usuario);

		Conta contaOrigem = transacaoService.contaExisteParaUsuario(transacaoInput.getContaOrigem());
		Conta contaDestino = transacaoService.encontraContaDestino(transacaoInput.getContaDestino());
		
		transacaoService.transacao(contaOrigem, contaDestino, transacaoInput.getValor());
		
		return ResponseEntity.ok(transacaoAssembler.toModel(transacaoInput));
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
