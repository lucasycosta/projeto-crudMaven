package br.com.lucas.projeto.teste.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.projeto.teste.domain.Usuario;
import br.com.lucas.projeto.teste.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Usuario cadastrar(@RequestBody @Valid Usuario usuario) {

		log.info("**Controller - Cadastrar Usuario");

		return usuarioService.cadastrar(usuario);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Usuario> buscarTodos() {

		log.info("Listar Todos os Usuario");

		return usuarioService.buscarTodos();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Usuario buscarPorId(@PathVariable Long id) {

		log.info("Buscar Usuario por id");

		return usuarioService.buscarPorId(id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void excluir(@PathVariable Long id) {

		log.info("Excluir Usuario por id");

		usuarioService.excluir(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public Map<String, String> handlerValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<String, String>();

		

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String errorMessage = error.getDefaultMessage();

			errors.put(fieldName, errorMessage);

		});

		return errors;

	}
}
