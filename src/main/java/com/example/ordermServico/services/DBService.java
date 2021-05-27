package com.example.ordermServico.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermServico.entities.Cliente;
import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.repositories.ClienteRepository;
import com.example.ordermServico.repositories.ServicoPrestadoRepository;

@Service
public class DBService {
	

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoPrestadoRepository servicoRepository;
	
	public void instantiateDatabase() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		clienteRepository.deleteAll();
		servicoRepository.deleteAll();
		
		Cliente c1 = new Cliente(null, "Ana Karolina", "61801405379", LocalDate.parse("2021-04-10"));
		Cliente c2 = new Cliente(null, "João Carlos", "61801405379", LocalDate.parse("2021-01-07"));
		Cliente c3 = new Cliente(null, "Mary Santos", "67025846368", LocalDate.parse("2020-03-16"));
		Cliente c4 = new Cliente(null, "Carlos Alberto", "86267183300", LocalDate.parse("2020-03-16"));
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		
		ServicoPrestado s1 = new ServicoPrestado(null, "Formatação Notebook", c1, 50.00,LocalDate.parse("2020-04-25"));
		ServicoPrestado s2 = new ServicoPrestado(null, "Troca de memoria",c2, 50.00,LocalDate.parse("2020-03-15"));
		ServicoPrestado s3 = new ServicoPrestado(null, "Conserto de geladeira", c1, 50.00,LocalDate.parse("2020-04-25"));
		ServicoPrestado s4 = new ServicoPrestado(null, "Conserto de Celular",c3, 50.00,LocalDate.parse("2020-04-25"));
		ServicoPrestado s5 = new ServicoPrestado(null, "Conserto de Celular",c4, 100.00,LocalDate.parse("2020-04-25"));
		servicoRepository.saveAll(Arrays.asList(s1,s2,s3,s4,s5));
		
		c1.getServicos().addAll(Arrays.asList(s1,s3));
		c2.getServicos().addAll(Arrays.asList(s2));
		c3.getServicos().addAll(Arrays.asList(s4));
		c4.getServicos().addAll(Arrays.asList(s5));
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
	}

}
