package br.com.lucas.projeto.teste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.projeto.teste.domain.Usuario;
import br.com.lucas.projeto.teste.repository.UsuarioRepository;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
public class UsuarioService {

	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	public Usuario cadastrar(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}
	
}
