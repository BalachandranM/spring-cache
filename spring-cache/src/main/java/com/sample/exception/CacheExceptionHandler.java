package com.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CacheExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStackTrace().toString());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
