package com.example.ordermServico.dto;

import java.io.Serializable;

import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.entities.enums.Status;

public class ServicoPrestadoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Integer clienteId;
	private Integer status;
	
	public ServicoPrestadoDTO() {
		super();
	}
	
	public ServicoPrestadoDTO(ServicoPrestado obj) {
		super();
		id = obj.getId();
		descricao = obj.getDescricao();
		clienteId = obj.getCliente().getId();
		status = obj.getStatus().getCod();
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

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}
}
