package com.example.ordermServico.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.ordermServico.services.ServicoPrestadoService;

@RestController
@RequestMapping(value="/servicos")
public class ServicoPrestadoResource {

	@Autowired
	private ServicoPrestadoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ServicoPrestadoDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(new ServicoPrestadoDTO(service.findById(id)));
	}
	
	@GetMapping
	public ResponseEntity<List<ServicoPrestadoDTO>> findAll(){
		List<ServicoPrestadoDTO> listDto = service.findAll().stream().map(x -> new ServicoPrestadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<ServicoPrestadoDTO> insert(@RequestBody ServicoPrestadoDTO obj){
		obj = new ServicoPrestadoDTO(service.insert(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ServicoPrestadoDTO> update(@PathVariable Long id, @RequestBody ServicoPrestadoDTO obj){
		obj = new ServicoPrestadoDTO(service.update(id, obj));
		return ResponseEntity.ok().build();
	}
}
