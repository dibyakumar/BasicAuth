package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.service.CustomUserDetailsService;

@Configuration
public class SecurityConfiguration {
	
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// if you will not disable csrf then we will not be able to perform post operations 
		http.csrf(csrf->csrf.disable()).
		authorizeHttpRequests(request->
		request.requestMatchers("/user/signin").permitAll()
		.requestMatchers("/user/check").hasRole("ADMIN")
		.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user1 = User.builder().username("dibya").password(passwordcoder().encode("1234")).roles("USER").build();
//		UserDetails user2 = User.builder().username("kumar").password(passwordcoder().encode("1234")).roles("USER","ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	
	
	@Bean
	public DaoAuthenticationProvider user() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordcoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordcoder() {
		return new BCryptPasswordEncoder();
	}

}
