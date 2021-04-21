package com.example.ordermServico.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ordermServico.services.exceptions.DatabaseException;
import com.example.ordermServico.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error,e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request){
		String error = "DataBase error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error,"Cliente possui serviço associados", request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		String erro = "Erro da validação";
		String message = "Campos incorretos";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		ValidationError validationErro = new ValidationError(Instant.now(), status.value(), erro, message, request.getRequestURI());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			validationErro.addErros(x.getDefaultMessage());
		}	
		return ResponseEntity.status(status).body(validationErro);	
	}
}
