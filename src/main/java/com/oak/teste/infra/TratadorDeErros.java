package com.oak.teste.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> error404 () {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> error400 (MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
	
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		
		String errorMessage = "Verifique o campo DISPONÍVEL";
		
        String errorCode = "INVALID_DATA";

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        errorResponse.put("code", errorCode);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
		
	public record DadosErros(String campo, String mensagem) {
		
		public DadosErros(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
