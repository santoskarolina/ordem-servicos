package com.example.ordermServico.resources;



import java.util.Arrays;
import java.util.List;

public class ApiErros {

   
    private List<String> errors;
    
    public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ApiErros(List<String> errors){
        this.errors = errors;
    }

    public ApiErros(String message){
        this.errors = Arrays.asList(message);
    }

}