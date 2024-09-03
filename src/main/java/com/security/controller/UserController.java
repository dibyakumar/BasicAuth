package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.entity.User;
import com.security.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService uservice;
	
	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody User user){
		 return new ResponseEntity<String>(uservice.signIn(user),HttpStatus.OK);
	}
	
	@GetMapping("/check")
	public ResponseEntity<String> check(){
		return new ResponseEntity<String>("You Are Authenticated and Secure",HttpStatus.OK);
	}
	
	
}
