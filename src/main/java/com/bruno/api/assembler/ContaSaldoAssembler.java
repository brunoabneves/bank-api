package com.bruno.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bruno.api.model.ContaSaldoModel;
import com.bruno.domain.model.Conta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ContaSaldoAssembler {
	
	private ModelMapper modelMapper;
	
	public ContaSaldoModel toModel(Conta conta) {
		return modelMapper.map(conta, ContaSaldoModel.class);
	}
}
