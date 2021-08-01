package com.bruno.repository;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Conta;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.ContaRepository;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.util.ContaCreator;
import com.bruno.util.UsuarioCreator;

@DataJpaTest
@DisplayName("Testes para o repositório de contas")
public class ContaRepositoryTest {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	@DisplayName("Salva uma conta quando bem sucedido")
	void save_PersisteConta_QuandoSucesso() {
		
		Conta contaASerSalva = ContaCreator.criaConta(usuarioSalvo());
		Conta contaSalva = this.contaRepository.save(contaASerSalva);
		
		Assertions.assertThat(contaSalva).isNotNull();
		Assertions.assertThat(contaSalva.getId()).isNotNull();
		Assertions.assertThat(contaSalva.getId()).isEqualTo(contaASerSalva.getId());
	}
	
	@Test
	@DisplayName("Retrona uma conta quando bem sucedido")
	void findByNumero_retornaConta_QuandoSucesso() {
		
		Conta contaASerSalva = ContaCreator.criaConta(usuarioSalvo());
		Conta contaSalva = this.contaRepository.save(contaASerSalva);
		
		String numero = contaSalva.getNumero();
		
		Conta conta = this.contaRepository.findByNumero(numero);
		
		Assertions.assertThat(conta).isNotNull();
		Assertions.assertThat(conta.getId()).isNotNull();
		Assertions.assertThat(conta.getNumero()).isEqualTo(contaSalva.getNumero());
	}
	
	@Test
	@DisplayName("Retrona Null quando conta não é encontrada")
	void findByNumero_retornaNull_QuandoContaNaoEncontrada() {
		
		Conta contaASerSalva = ContaCreator.criaConta(usuarioSalvo());
		this.contaRepository.save(contaASerSalva);
		Conta conta = this.contaRepository.findByNumero("2222-2");
		
		Assertions.assertThat(conta).isNull();
	}
	
	@Test
	@DisplayName("Retrona uma conta quando bem sucedido")
	void findByUsuarioIdAndNumero_retornaConta_QuandoSucesso() {
		
		Conta contaASerSalva = ContaCreator.criaConta(usuarioSalvo());
		Conta contaSalva = this.contaRepository.save(contaASerSalva);
		
		String numero = contaSalva.getNumero();
		Long usuarioId = contaSalva.getUsuario().getId();
		
		Conta conta = this.contaRepository.findByUsuarioIdAndNumero(usuarioId, numero);
		
		Assertions.assertThat(conta).isNotNull();
		Assertions.assertThat(conta.getId()).isNotNull();
		Assertions.assertThat(conta.getNumero()).isNotNull();
		Assertions.assertThat(conta.getNumero()).isEqualTo(contaSalva.getNumero());
		Assertions.assertThat(conta.getUsuario().getId()).isEqualTo(usuarioId);
	}
	
	@Test
	@DisplayName("Retrona Null quando conta não é encontrada")
	void findByUsuarioIdAndNumero_retornaNull_QuandoContaNaoEncontrada() {
		
		Conta contaASerSalva = ContaCreator.criaConta(usuarioSalvo());
		this.contaRepository.save(contaASerSalva);
		
		//Long usuarioId = contaSalva.getUsuario().getId();
		Conta conta = this.contaRepository.findByUsuarioIdAndNumero(1L,"1234-8");
		
		Assertions.assertThat(conta).isNull();
	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando número está em branco")
	void save_ThrowConstraintViolationException_QuandoNomeEmBranco() {
		
		Conta conta = ContaCreator.criaConta(usuarioSalvo());
		conta.setNumero("");
		
		Assertions.assertThatThrownBy(() -> this.contaRepository.save(conta))
				.isInstanceOf(ConstraintViolationException.class);

	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando ultrapassa limite do size do número")
	void save_ThrowConstraintViolationException_QuandoSizeNumeroUltrapassaLimite() {
		
		Conta conta = ContaCreator.criaConta(usuarioSalvo());
		conta.setNumero("asdfffa");
		
		Assertions.assertThatThrownBy(() -> this.contaRepository.save(conta))
				.isInstanceOf(ConstraintViolationException.class);

	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando número não atinge limite mínimo de caracteres")
	void save_ThrowConstraintViolationException_QuandoNumeroNaoAtingeCaracteresMinimos() {
		
		Conta conta = ContaCreator.criaConta(usuarioSalvo());
		conta.setNumero("asff");
		
		Assertions.assertThatThrownBy(() -> this.contaRepository.save(conta))
				.isInstanceOf(ConstraintViolationException.class);

	}
	
	@Test
	@DisplayName("Salvar throw ConstraintViolationException quando saldo é nulo")
	void save_ThrowConstraintViolationException_QuandoSaldoNulo() {
		
		Conta conta = ContaCreator.criaConta(usuarioSalvo());
		conta.setSaldo(null);
		
		Assertions.assertThatThrownBy(() -> this.contaRepository.save(conta))
				.isInstanceOf(ConstraintViolationException.class);

	}
	
	Usuario usuarioSalvo() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioValido();
		Usuario usuario = this.usuarioRepository.save(usuarioASerSalvo);
		
		return usuario;
	}
}
