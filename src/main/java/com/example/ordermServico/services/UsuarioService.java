package com.example.ordermServico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ordermServico.entities.Usuario;
import com.example.ordermServico.repositories.UsuarioRepository;
import com.example.ordermServico.services.exceptions.UsuarioCadastradoException;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;

	public Usuario insert(Usuario obj) {
		boolean exists  = repository.existsByNome(obj.getNome());
		if(exists) {
			throw new UsuarioCadastradoException(obj.getNome());
		}
		return repository.save(obj);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario  = repository.findByNome(username).
				orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));
		
		return User.builder().username(usuario.getNome()).password(usuario.getSenha()).roles("USER").build();
	}	
}
