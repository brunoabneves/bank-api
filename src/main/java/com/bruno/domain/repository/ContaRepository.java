package com.bruno.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Conta;

@Repository
public interface ContaRepository  extends JpaRepository<Conta, Long>{

	Conta findByNumero(String numero);

	Conta findByUsuarioIdAndNumero(Long id, String contaOrigem);
	
}
