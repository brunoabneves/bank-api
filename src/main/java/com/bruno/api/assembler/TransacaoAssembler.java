package com.bruno.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bruno.api.model.TransacaoModel;
import com.bruno.api.model.input.TransacaoInput;
import com.bruno.domain.model.Transacao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TransacaoAssembler {

private ModelMapper modelMapper;
	
	public TransacaoModel toModel(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoModel.class);
	}
	
	public Transacao toEntity(TransacaoInput transacaoInput) {
		return modelMapper.map(transacaoInput, Transacao.class);
	}
	
	public TransacaoModel toInputModel(TransacaoInput transacaoInput) {
		return modelMapper.map(transacaoInput, TransacaoModel.class);
	}
}
