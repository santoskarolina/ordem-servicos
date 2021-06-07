package com.example.ordermServico.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermServico.dto.ServicoPrestadoDTO;
import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.entities.enums.Status;
import com.example.ordermServico.repositories.ServicoPrestadoRepository;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository repository;

	public List<ServicoPrestado> findAll() {
		return repository.findAll();
	}

	public ServicoPrestado findById(Integer id) {
		Optional<ServicoPrestado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public ServicoPrestado insert(ServicoPrestado obj) {
		return repository.save(obj);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public ServicoPrestado update(Integer id, ServicoPrestadoDTO obj) {
		try {
			ServicoPrestado oldObj = repository.getOne(id);
			updateDate(obj,oldObj);
			return repository.save(oldObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// transformar um dto em uma entity
	public ServicoPrestado fromDTO(ServicoPrestadoDTO obj) {
		Cliente client = new Cliente(obj.getClienteId(), null, null, null);

		ServicoPrestado newService = new ServicoPrestado(null, obj.getDescricao(), client, obj.getValor());
		client.getServicos().add(newService);
		newService.setCliente(client);
		return newService;
	}

	// atualizar os dados do servico
	public ServicoPrestado updateDate(ServicoPrestadoDTO newobj, ServicoPrestado oldOrder) {
		oldOrder.setDescricao(newobj.getDescricao());
		oldOrder.setValor(newobj.getValor());
		oldOrder.setStatus(newobj.getStatus());
		
		if(newobj.getStatus().equals(Status.FINALIZADO)) {
			oldOrder.setDataFechamento(new Date());
		}
		return oldOrder;
	}
}
