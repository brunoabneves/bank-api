package com.bruno.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.TransacaoAssembler;
import com.bruno.api.model.TransacaoModel;
import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Transacao;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.service.TransacaoService;
import com.bruno.domain.service.UsuarioService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class TransferenciaController {

	private UsuarioService usuarioService;
	private TransacaoService transacaoService;
	private TransacaoAssembler transacaoAssembler;
	
	@PutMapping("/accounts/transfer")
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
}
