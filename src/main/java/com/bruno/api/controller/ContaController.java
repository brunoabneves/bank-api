package com.bruno.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.ContaAssembler;
import com.bruno.api.assembler.TransacaoAssembler;
import com.bruno.api.model.ContaModel;
import com.bruno.api.model.TransacaoModel;
import com.bruno.api.model.input.ContaInput;
import com.bruno.api.model.input.TransacaoInput;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Transacao;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.ContaRepository;
import com.bruno.domain.service.CadastroContaService;
import com.bruno.domain.service.TransacaoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class ContaController {

	private CadastroContaService cadastroContaService;
	private ContaAssembler contaAssembler;
	private TransacaoService transacaoService;
	private ContaRepository contaRepository;
	private TransacaoAssembler transacaoAssembler;
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public ContaModel cadastrar(@Valid @RequestBody ContaInput contaInput) {
		
		Usuario usuario = cadastroContaService.getUsuarioLogado();
		
		Conta novaConta = contaAssembler.toEntity(contaInput);
		novaConta.setUsuario(usuario);
		
		return contaAssembler.toModel(cadastroContaService.salvar(novaConta));
	}
	
	@PutMapping("/transfer")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<TransacaoModel> transacaoEntreContas(@RequestBody Transacao transacaoInput) {
		Usuario usuario = cadastroContaService.getUsuarioLogado();
		transacaoInput.setUsuario(usuario);

		Conta contaOrigem = transacaoService.contaExisteParaUsuario(transacaoInput.getContaOrigem());
		Conta contaDestino = contaRepository.findByNumero(transacaoInput.getContaDestino());
		
		transacaoService.transacao(contaOrigem, contaDestino, transacaoInput.getValor());
		
		return ResponseEntity.ok(transacaoAssembler.toModel(transacaoInput));
	}
	
}
