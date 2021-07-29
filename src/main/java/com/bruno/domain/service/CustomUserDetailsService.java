package com.bruno.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usuario =  Optional.ofNullable(usuarioRepository.findByEmail(email))
				.orElseThrow(()-> new UsernameNotFoundException("User not found!"));
		
		List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), authorityListUser);
		
	}

}
