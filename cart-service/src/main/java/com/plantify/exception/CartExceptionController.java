package com.plantify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.plantify.cart.model.ExceptionMessage;

@ControllerAdvice
public class CartExceptionController {
	@ExceptionHandler(value = CartItemNotFoundException.class)
	public ResponseEntity<ExceptionMessage> cartItemNotFoundException(CartItemNotFoundException cartexception) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), cartexception.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ServiceDownException.class)
	public ResponseEntity<ExceptionMessage> serviceDownException(ServiceDownException serviceexception) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), serviceexception.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
	}

}
