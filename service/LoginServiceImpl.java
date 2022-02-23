package com.example.demo.service;

import java.util.UUID;

public interface LoginServiceImpl {
	public boolean check(String authorizeId);
	public String generate();
	
	public boolean remove(String value);

}
