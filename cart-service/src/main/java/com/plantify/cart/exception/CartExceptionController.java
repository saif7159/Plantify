package com.plantify.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.plantify.cart.model.ExceptionMessage;

@ControllerAdvice
public class CartExceptionController {
	@ExceptionHandler(value = CartItemNotFoundException.class)
	public ResponseEntity<ExceptionMessage> cartItemNotFound(CartItemNotFoundException cartexception) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), cartexception.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ServiceDownException.class)
	public ResponseEntity<ExceptionMessage> serviceDown(ServiceDownException serviceexception) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), serviceexception.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
	}
}
