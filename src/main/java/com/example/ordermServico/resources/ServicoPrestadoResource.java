package com.example.ordermServico.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ordermServico.dto.ServicoPrestadoDTO;
import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.services.ServicoPrestadoService;

@RestController
@RequestMapping(value="/servicos")
public class ServicoPrestadoResource {

	@Autowired
	private ServicoPrestadoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ServicoPrestado> findById(@PathVariable Integer id){
		ServicoPrestado obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<ServicoPrestadoDTO>> findAll(){
		List<ServicoPrestadoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<ServicoPrestadoDTO> insert(@RequestBody @Valid ServicoPrestadoDTO objDto){
		ServicoPrestado obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ServicoPrestado> update(@PathVariable Integer id, @Valid @RequestBody ServicoPrestadoDTO objDto){
		ServicoPrestado obj = service.fromDTO(objDto);
		obj = service.update(id, objDto);
		return ResponseEntity.ok().body(obj);
	}
}
