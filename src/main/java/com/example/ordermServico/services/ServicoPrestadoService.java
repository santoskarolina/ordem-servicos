package com.example.ordermServico.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermServico.dto.ServicoPrestadoDTO;
import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.repositories.ServicoPrestadoRepository;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository repository;
	
	
	public List<ServicoPrestado> findAll(){
		return repository.findAll();
	}
	
	public ServicoPrestado findById(Integer id) {
		Optional<ServicoPrestado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public ServicoPrestado insert(ServicoPrestado obj) {
		return repository.save(obj);
	}
	
	public ServicoPrestado fromDTO(ServicoPrestadoDTO obj) {
		Cliente client = new Cliente(obj.getClienteId(), null, null, null);
		
		ServicoPrestado newService = new ServicoPrestado(null, obj.getDescricao(), client, obj.getValor(),obj.getData());
		
		client.getServicos().addAll(Arrays.asList(newService));
		newService.setCliente(client);
		return newService;
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public ServicoPrestado update(Integer id, ServicoPrestadoDTO obj) {
		try {
			ServicoPrestado oldOrder = repository.getOne(id);
			oldOrder = updateDate(oldOrder, obj);
			return repository.save(oldOrder);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private ServicoPrestado updateDate(ServicoPrestado oldOrder, ServicoPrestadoDTO obj) {
		oldOrder.setDescricao(obj.getDescricao());
		oldOrder.setValor(obj.getValor());
		oldOrder.setData(obj.getData());
		return null;
	}

}
