package com.bruno.api.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.bruno.api.model.ContaModel;
import com.bruno.api.model.input.ContaInput;
import com.bruno.domain.model.Conta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ContaAssembler {

private ModelMapper modelMapper;
	
	public ContaModel toModel(Conta conta) {
		return modelMapper.map(conta, ContaModel.class);
	}
	
	public List<ContaModel> toCollectionModel(List<Conta> contas) {
		return contas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public List<ContaModel> toPageModel(Page<Conta> page) {
		return page.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Conta toEntity(ContaInput contaInput) {
		return modelMapper.map(contaInput, Conta.class);
	}

	public Conta toEntity(Optional<Conta> conta) {
		return modelMapper.map(conta, Conta.class);
	}
	
}
