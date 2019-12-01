package com.example.mongo.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.mongo.services.exception.ResourceNotFoundException;
import com.example.mongo.services.exception.SaveException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest r){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(Instant.now(), status.value(), "Recurso não encontrado", e.getMessage(), r.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(SaveException.class)
	public ResponseEntity<StandardError> save(SaveException e, HttpServletRequest r) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError error = new StandardError(Instant.now(), status.value(), "Usuário Vazio", e.getMessage(), r.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
}
