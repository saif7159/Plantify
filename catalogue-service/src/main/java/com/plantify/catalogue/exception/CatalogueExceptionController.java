package com.plantify.catalogue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.plantify.catalogue.model.ExceptionMessage;

@ControllerAdvice
public class CatalogueExceptionController {
	@ExceptionHandler(value = ItemNotFoundException.class)
	public ResponseEntity<ExceptionMessage> itemNotFound(ItemNotFoundException itemexception) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), itemexception.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);

	}
}
