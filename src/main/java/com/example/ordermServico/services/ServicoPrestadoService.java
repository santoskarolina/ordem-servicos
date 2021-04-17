package com.example.ordermServico.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermServico.dto.ServicoPrestadoDTO;
import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.repositories.ClienteRepository;
import com.example.ordermServico.repositories.ServicoPrestadoRepository;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<ServicoPrestado> findAll(){
		return repository.findAll();
	}
	
	public ServicoPrestado findById(Long id) {
		Optional<ServicoPrestado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public ServicoPrestado insert(ServicoPrestadoDTO obj) {
		return repository.save(fromDTO(obj));
	}
	
	private ServicoPrestado fromDTO(ServicoPrestadoDTO obj) {
		ServicoPrestado newObj = new ServicoPrestado();
		newObj.setId(obj.getId());
		newObj.setDescricao(obj.getDescricao());
		newObj.setData(obj.getData());
		newObj.setValor(obj.getValor());
		
		Cliente client = clienteService.findById(obj.getCliente());
		clienteRepository.save(client);
		
		newObj.setCliente(client);
		return newObj;
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public ServicoPrestado update(Long id, ServicoPrestadoDTO obj) {
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
