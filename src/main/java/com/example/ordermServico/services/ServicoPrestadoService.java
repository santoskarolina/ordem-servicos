package com.example.ordermServico.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.ordermServico.dto.ServicoPrestadoNewDTO;
import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.entities.enums.Status;
import com.example.ordermServico.repositories.ServicoPrestadoRepository;
import com.example.ordermServico.services.exceptions.DatabaseException;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository repository;
	
	@Autowired
	private ClienteService clienteService;

	public List<ServicoPrestado> findAll() {
		List<ServicoPrestado> list = repository.findAll();
		return list;
	}

	public ServicoPrestado findById(Integer id) {
		Optional<ServicoPrestado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public ServicoPrestado insert(ServicoPrestadoNewDTO objdto) {
		ServicoPrestado obj = fromDTO(objdto);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		}catch(ConstraintViolationException e) {
			throw new DatabaseException("Serviço possui cliente associado");
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Serviços possui clientes");
		}
	}

	public ServicoPrestado update(Integer id, ServicoPrestadoNewDTO obj) {
		try {
			ServicoPrestado oldObj = repository.getOne(id);
			updateDate(obj,oldObj);
			return repository.save(oldObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// transformar um dto em uma entity
	public ServicoPrestado fromDTO(ServicoPrestadoNewDTO obj) {
		Cliente client = new Cliente(obj.getCliente(), null, null, null, null);

		ServicoPrestado newService = new ServicoPrestado(null, obj.getDescricao(), client, obj.getValor());
		client.getServicos().add(newService);
		newService.setCliente(client);
		return newService;
	}

	// atualizar os dados do servico
	public ServicoPrestado updateDate(ServicoPrestadoNewDTO newobj, ServicoPrestado oldOrder) {
		oldOrder.setDescricao(newobj.getDescricao());
		oldOrder.setValor(newobj.getValor());
		oldOrder.setStatus(newobj.getStatus());
		oldOrder.setCliente(clienteService.findById(newobj.getCliente()));
		
		if(newobj.getStatus().equals(Status.FINALIZADO)) {
			oldOrder.setDataFechamento(new Date());
		}else {
			oldOrder.setDataFechamento(null);
		}
		return oldOrder;
	}
}
