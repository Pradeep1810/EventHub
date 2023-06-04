package com.eventhub.exceptions;





import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;







@RestControllerAdvice
public class AllExceptionsHandler {
	
		
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException em){
		
		String message = em.getMessage();
		
		return  ResponseEntity.ok(message);

	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> apiExceptionHandler(ApiException em)
	
	{
		
        String message = em.getMessage();
		
		return  ResponseEntity.ok(message);	
	}
	
	@ExceptionHandler(SQLIntegrityViolationException.class)
	public ResponseEntity<?> handleSQLIntegrityViolationException(SQLIntegrityViolationException ex) {
	    String message = ex.getMessage();
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
}
