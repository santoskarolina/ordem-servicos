package com.example.ordermServico.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.repositories.ClienteRepository;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public Cliente update(Long id, Cliente obj) {
		try {
			Cliente entity = repository.getOne(id);
			updateDate(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateDate(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
	}
}
