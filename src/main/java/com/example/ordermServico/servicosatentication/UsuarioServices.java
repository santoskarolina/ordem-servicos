package com.example.ordermServico.servicosatentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ordermServico.entities.Usuario;
import com.example.ordermServico.repositories.UsuarioRepository;


@Service
public class UsuarioServices implements UserDetailsService{
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario  = usuarioRepository.findByNome(username).
				orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));
		
		return User.builder().username(usuario.getNome()).password(usuario.getSenha()).roles("USER").build();
	}
}
