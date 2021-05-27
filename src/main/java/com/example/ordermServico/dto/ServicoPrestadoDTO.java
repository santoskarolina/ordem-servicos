package com.example.ordermServico.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.example.ordermServico.entities.ServicoPrestado;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ServicoPrestadoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotBlank(message = "Descrição é obrigatória")
	@NotEmpty(message = "Descrição é obrigatória")
	private String descricao;

	private Integer clienteId;
	
	private Double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	public ServicoPrestadoDTO() {
	}

	public ServicoPrestadoDTO(ServicoPrestado obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.clienteId = obj.getCliente().getId();
		this.valor = obj.getValor();
		this.data = obj.getData();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
