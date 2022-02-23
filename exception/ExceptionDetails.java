package com.example.demo.exception;

import java.time.LocalDateTime;

public class ExceptionDetails {
	
	private LocalDateTime dateTime;
	private String message;
	private String details;
	
	
	public ExceptionDetails(LocalDateTime dateTime, String message, String details) {
		super();
		this.dateTime = dateTime;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	

}
