package com.example.ordermServico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ordermServico.dto.ClienteDTO;
import com.example.ordermServico.dto.ClienteNewDTO;
import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.repositories.ClienteRepository;
import com.example.ordermServico.services.exceptions.DatabaseException;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<ClienteDTO> findAll(){
		List<Cliente> list = repository.findAll();
		List<ClienteDTO> dto = list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
		return dto;
	}
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente insert(ClienteNewDTO objdto) {
		Cliente obj = fromDTO(objdto);
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Cliente update(Integer id, ClienteNewDTO obj) {
		try {
			Cliente entity = repository.getOne(id);
			updateDate(obj, entity);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public Cliente fromDTO(ClienteNewDTO objdto) {
		Cliente obj = new Cliente(null, objdto.getNome(), objdto.getCpf(), objdto.getDataCadastro(), objdto.getTelefone());
		return obj;
	}

	private void updateDate(ClienteNewDTO newobj, Cliente oldobj) {
		oldobj.setNome(newobj.getNome());
		oldobj.setCpf(newobj.getCpf());
		oldobj.setTelefone(newobj.getTelefone());
	}
}
