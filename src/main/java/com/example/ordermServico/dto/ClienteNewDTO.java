package com.example.ordermServico.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.example.ordermServico.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Nome obrigatório")
	private String nome;

	@NotEmpty(message = "CPF obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;

	@NotEmpty(message = "Telefone obrigatório")
	private String telefone;

	public ClienteNewDTO() {
	}

	public ClienteNewDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.dataCadastro = obj.getDataCadastro();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
