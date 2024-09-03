package com.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.entity.CustomUserDetails;
import com.security.entity.User;
import com.security.repository.UserRepo;

@Service
public class CustomUserDetailsService  implements UserDetailsService{
		
	@Autowired
	private UserRepo urepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> findById = urepo.findById(username);
		
		if(findById.isEmpty()) throw new UsernameNotFoundException("User Not Found !!");
		return new CustomUserDetails(findById.get());
	}
	

}
