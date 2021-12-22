package br.com.lucas.projeto.teste.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import br.com.lucas.projeto.teste.domain.Usuario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioTest {
	@Autowired
	private UsuarioController usuarioController;
	
	private Usuario popularUsuario() {
		log.debug("Usuario padrão");
		Usuario usuario = new Usuario();
		usuario.setNome("Teste da Silva");
		usuario.setEmail("teste@mail.com");
		usuario.setCpf("255.766.360-31");
		return usuario;
	}
	
	private static Long id;
	
	@Order(1)
	@Test
	public void naoDevoCadastrarUsuarioComNomeVazio() {
		log.info("**TEST - naoDevoCadastrarUsuarioComNomeVazio");
		
		log.debug("Deixar o campo 'nome' vazio");
		Usuario usuario = popularUsuario();
		usuario.setNome("");
		
		try {
			log.info("Gravar Usuario");
			usuarioController.cadastrar(usuario);	
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
		        String campo = violations.iterator().next().getPropertyPath().toString();
		        String mensagem = violations.iterator().next().getMessage(); 
		        log.debug(campo + ": " + mensagem);
		        assertEquals("Campo não informado", mensagem);
		    }
		}
	}
	@Order(1)
	@Test
	public void naoDevoCadastrarUsuarioComNomeIniciadoComLetraMinuscula() {
		log.info("** Test - naoDevoCadastrarUsuarioComNomeIniciadoComLetraMinuscula");
		
		log.debug("Adiconar nome que comece com letra minuscula");
		Usuario usuario = popularUsuario();
		usuario.setNome("lucas");
		
		try {
			log.info("Gravar Usuario");
			usuarioController.cadastrar(usuario);	
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
		        String campo = violations.iterator().next().getPropertyPath().toString();
		        String mensagem = violations.iterator().next().getMessage(); 
		        log.debug(campo + ": " + mensagem);
		        assertEquals("Nome deve começar com letra maiúscula", mensagem);
		    }
		}
	}
	@Order(1)
	@Test
	public void naoDevoCadastrarUsuarioComEmailInvalido() {
		log.info("**TEST - naoDevoCadastrarUsuarioComEmailInvalido");
		
		log.debug("Email sem padrão");
		Usuario usuario = popularUsuario();
		usuario.setEmail("lucas.com");
		
		try {
			log.info("Gravar Usuario");
			usuarioController.cadastrar(usuario);	
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
		        String campo = violations.iterator().next().getPropertyPath().toString();
		        String mensagem = violations.iterator().next().getMessage(); 
		        log.debug(campo + ": " + mensagem);
		        assertEquals("Email inválido", mensagem);
		    }
		}
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComEmailVazio() {
		log.info("** Test - naoDevoCadastrarUsuarioComEmailVazio");
		
		log.debug("Deixar e-mail vazio");
		Usuario usuario = popularUsuario();
		usuario.setEmail("");
		
		try {
			log.info("Gravar Usuario");
			usuarioController.cadastrar(usuario);	
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
		        String campo = violations.iterator().next().getPropertyPath().toString();
		        String mensagem = violations.iterator().next().getMessage(); 
		        log.debug(campo + ": " + mensagem);
		        assertEquals("Campo não informado", mensagem);
		    }
		}
	}
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComCPFVazio() {
		log.info("** Test - naoDevoCadastrarUsuarioComCPFVazio");
		
		log.debug("Deixar nome vazio");
		Usuario usuario = popularUsuario();
		usuario.setCpf("");
		
		try {
			log.info("Gravar Usuario");
			usuarioController.cadastrar(usuario);	
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
		        String campo = violations.iterator().next().getPropertyPath().toString();
		        String mensagem = violations.iterator().next().getMessage(); 
		        log.debug(campo + ": " + mensagem);
		        assertEquals("Campo não informado", mensagem);
		    }
		}
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComCPFInvalido() {
		log.info("** Test - naoDevoCadastrarUsuarioComCPFInvalido");
		
		log.debug("Deixar nome vazio");
		Usuario usuario = popularUsuario();
		usuario.setCpf("012.345.678-00");
		
		try {
			log.info("Gravar Usuario");
			usuarioController.cadastrar(usuario);	
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
		        Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
		        String campo = violations.iterator().next().getPropertyPath().toString();
		        String mensagem = violations.iterator().next().getMessage(); 
		        log.debug(campo + ": " + mensagem);
		        assertEquals("CPF inválido", mensagem);
		    }
		}
	}
	
	@Test
	@Order(2)
	public void devoCadastrarUsuario() {
		log.info("** Test - devoCadastrarUsuario");

		log.info("Gravar Usuario");
		Usuario usuario = usuarioController.cadastrar(popularUsuario());
		assertNotNull(usuario.getId(), "id não nulo");
	}
	
	@Test
	@Order(3)
	public void devoBuscarTodosUsuarios() {
		log.info("** Test - devoBuscarTodosUsuarios");

		log.info("Buscar todos usuarios");
		List<Usuario> lista = usuarioController.buscarTodos();
		
		int tamanho = lista.size();
		assertNotEquals(0, tamanho, "Lista de usuarios igual a zero");
		id = lista.get(tamanho-1).getId();
	}
	@Order(4)
	@Test
	public void buscarUsuarioPorId() {
		log.info("**TEST - buscarUsuarioPorId ");
		Usuario usuario = usuarioController.buscarPorId((long) 93);
		assertEquals("João", usuario.getNome());
	}
	

	@Test
	@Order(5)
	public void devoAtualizarUsuario() {
		log.info("** Test - devoBuscarTodosUsuarios");

		log.debug("Alterar usuario");
		Usuario usuario = usuarioController.buscarPorId(id);
		usuario.setNome("Pedro Alvares Cabral");
		
		log.info("Atualizar usuario");
		usuario = usuarioController.cadastrar(usuario);
		assertEquals("Pedro Alvares Cabral", usuario.getNome(), "Usuario diferente");
	}
	
	@Test
	@Order(6)
	public void devoExcluirUsuarioPorId() {
		log.info("** Test - devoBuscarTodosUsuarios");

		log.info("Excluir usuario");
		usuarioController.excluir(id);
		
		Usuario usuario = usuarioController.buscarPorId(id);
		assertNull(usuario, "Usuario não foi excluido");
	}

}
