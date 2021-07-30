package com.bruno.api.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.bruno.api.model.UsuarioModel;
import com.bruno.api.model.input.UsuarioInput;
import com.bruno.domain.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

	private ModelMapper modelMapper;
	
	public UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}
	
	public List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public List<UsuarioModel> toPageModel(Page<Usuario> page) {
		return page.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Usuario toEntity(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public Usuario toEntity(Optional<Usuario> usuario) {
		return modelMapper.map(usuario, Usuario.class);
	}
}
