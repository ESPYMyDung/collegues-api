package dev.colleguesapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import dev.colleguesapi.exception.CollegueInvalideException;
import dev.colleguesapi.exception.CollegueNonTrouveException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
{
	// la méthode handleConflict est exécutée lorsqu'un contrôleur émet une exception présente
	// dans la liste définie par l'annotation @ExceptionHandler
	@ExceptionHandler(value = { CollegueNonTrouveException.class, CollegueInvalideException.class })
	protected ResponseEntity<?> handleConflict(Exception e, WebRequest request) {
		return ResponseEntity.status(404).body(e.getMessage());
	}
}
