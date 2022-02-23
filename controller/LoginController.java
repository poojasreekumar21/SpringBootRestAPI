package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AuthorizationDetailsNotFoundException;
import com.example.demo.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/signin")
	public ResponseEntity<String> login(){
		String uuid=loginService.generate();
		 HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("authorizeId", uuid);

		   // return ResponseEntity.ok()
		     // .headers(responseHeaders)
		      //.body("Response with UUID");
		    return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/signoff")
	public ResponseEntity<String> logOff(@RequestHeader	(required=false,value="authorizeId") String authorizeId){
		
		Boolean removed=loginService.remove(authorizeId);
		if(removed) {
		 return new ResponseEntity<>("Removed", HttpStatus.OK);
		}
		throw new AuthorizationDetailsNotFoundException(" authorization id not present ");
		
	}

}
