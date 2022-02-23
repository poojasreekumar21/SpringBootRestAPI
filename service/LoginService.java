package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImpl {

	private static List<String> uuidList=new ArrayList<>();
	
	@Override
	public boolean check(String authorizeId) {
		// TODO Auto-generated method stub
		if(uuidList.contains(authorizeId))
			return true;
		return false;
	}
	
	@Override
	public String generate() {
		UUID uuid=UUID.randomUUID();
		uuidList.add(uuid.toString());
		return uuid.toString();
	}
	
	@Override
	public boolean remove(String value) {
		if(!uuidList.isEmpty()) {
			uuidList.remove(value);
			return true;
		}
		else {
			return false;
		}
	}


}
