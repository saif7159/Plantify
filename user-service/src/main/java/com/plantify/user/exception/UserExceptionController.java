package com.plantify.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.plantify.user.model.ExceptionMessage;

@ControllerAdvice
public class UserExceptionController {
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ExceptionMessage> userNotFound(UserNotFoundException userexception) {
		ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), userexception.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);

	}
}
