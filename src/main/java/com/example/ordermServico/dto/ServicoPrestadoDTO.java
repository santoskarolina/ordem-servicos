package com.example.ordermServico.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.example.ordermServico.entities.ServicoPrestado;

public class ServicoPrestadoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String descricao;

	private Long cliente;
	
	private Double valor;
	
	private LocalDate data;
	
	public ServicoPrestadoDTO() {
	}

	public ServicoPrestadoDTO(ServicoPrestado obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.cliente = obj.getCliente().getId();
		this.valor = obj.getValor();
		this.data = obj.getData();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
