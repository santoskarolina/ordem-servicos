package com.example.ordermServico.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError() {
	}
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErros() {
		return errors;
	}

	public void setErros(List<FieldMessage> errors) {
		this.errors = errors;
	}
	
	public void addErros(String fieldName, String mensagem) {
		errors.add(new FieldMessage(fieldName,mensagem));
	}
}
