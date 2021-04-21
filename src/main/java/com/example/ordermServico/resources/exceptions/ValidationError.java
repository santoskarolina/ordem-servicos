package com.example.ordermServico.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<String> errors = new ArrayList<>();
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<String> getErrros() {
		return errors;
	}

	public void setErrros(List<String> errors) {
		this.errors = errors;
	}
	
	public void addErros(String message) {
		errors.add(message);
	}
}
