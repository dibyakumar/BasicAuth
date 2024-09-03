package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.entity.User;
import com.security.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo urepo;
	
	@Autowired
	PasswordEncoder passwordEncoder ;
	
	public String signIn(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		urepo.save(user);
		return "SignedIn SuccessFully";
	}
	
}
