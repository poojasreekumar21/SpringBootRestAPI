package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,WebRequest request)
	{
		return new ResponseEntity<>("Author not present",HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BooksNotFoundException.class)
	public final ResponseEntity<Object> handleBooksNotFoundException(BooksNotFoundException ex,WebRequest request)
	{
		ExceptionDetails exp=new ExceptionDetails(LocalDateTime.now(),ex.getMessage()," Book not present ");
		return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BookDetailsNotFoundException.class)
	public final ResponseEntity<Object> handleBookDetailsNotFoundException(BookDetailsNotFoundException ex,WebRequest request)
	{
		return new ResponseEntity<>(" The current book details not present 	",HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(AuthorizationDetailsNotFoundException.class)
	public final ResponseEntity<Object> handleAuthorizationDetailsNotFoundException(AuthorizationDetailsNotFoundException ex,WebRequest request)
	{
		ExceptionDetails exp=new ExceptionDetails(LocalDateTime.now(),ex.getMessage(),"Include valid authorization Id ");
		return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionDetails exp=new ExceptionDetails(LocalDateTime.now(),"Validation Failed",ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
	}
}
