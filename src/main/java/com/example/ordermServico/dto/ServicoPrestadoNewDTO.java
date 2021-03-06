package com.example.ordermServico.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.example.ordermServico.entities.ServicoPrestado;
import com.example.ordermServico.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ServicoPrestadoNewDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(message = "Descrição é obrigatória")
	private String descricao;

	private Integer cliente;
	
	private Double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataFechamento;
	
	private Integer status;
	
	public ServicoPrestadoNewDTO() {
		super();
	}
	
	public ServicoPrestadoNewDTO(ServicoPrestado obj) {
		super();
		id = obj.getId();
		descricao = obj.getDescricao();
		cliente = obj.getCliente().getId();
		valor = obj.getValor();
		status = obj.getStatus().getCod();
		dataAbertura = obj.getDataAbertura();
		dataFechamento = obj.getDataFechamento();
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

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer clienteId) {
		this.cliente = clienteId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}
}
