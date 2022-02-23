package com.example.demo.exception;

public class BookDetailsNotFoundException extends RuntimeException{
	public BookDetailsNotFoundException(String message) {
		super(message);
	}

}
