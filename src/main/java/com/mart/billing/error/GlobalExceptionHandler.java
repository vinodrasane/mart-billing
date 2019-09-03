package com.mart.billing.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final String BAD_REQUEST_CODE = "10";

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorMessage> handleCustomExceptions(CustomException exp){
		ErrorMessage err = new ErrorMessage(exp.getCode(), exp.getMessage());
		return ResponseEntity.unprocessableEntity().body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleInvalidArguments(MethodArgumentNotValidException exp){
		ErrorMessage err = new ErrorMessage(BAD_REQUEST_CODE, exp.getMessage());
		return ResponseEntity.badRequest().body(err);
	}
}
