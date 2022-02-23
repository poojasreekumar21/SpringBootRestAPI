package com.example.demo.exception;

public class AuthorizationDetailsNotFoundException extends RuntimeException {
	
	public AuthorizationDetailsNotFoundException(String message) {
		super(message);
	}

}
