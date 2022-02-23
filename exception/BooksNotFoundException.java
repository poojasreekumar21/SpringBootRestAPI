package com.example.demo.exception;

public class BooksNotFoundException extends RuntimeException {
	
	public BooksNotFoundException(String message) {
		super(message);
	}
}
	
