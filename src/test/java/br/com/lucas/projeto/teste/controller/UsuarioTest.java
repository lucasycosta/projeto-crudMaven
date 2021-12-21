package br.com.lucas.projeto.teste.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.lucas.projeto.teste.domain.Usuario;

@SpringBootTest
public class UsuarioTest {
	@Autowired
	private UsuarioController usuarioController;
	@Test
	public void buscarUsuarioPorId() {
		Usuario usuario = usuarioController.buscarPorId((long) 1);
//		assertNotNull(usuario, "usuario nulo");
		assertEquals("Lucas", usuario.getNome());
	}

}
