package com.example.ordermServico.dto;

import java.io.Serializable;

import com.example.ordermServico.entities.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String nome;
	private String telefone;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
