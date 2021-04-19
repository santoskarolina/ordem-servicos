package com.example.ordermServico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermServico.entities.Usuario;
import com.example.ordermServico.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario insert(Usuario obj) {
		return repository.save(obj);
	}
	
	
}
