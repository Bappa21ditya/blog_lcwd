package com.blog.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.project.paylods.JwtAuthRequest;
import com.blog.project.security.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwttokenhelper;
	
	@Autowired 
	private UserDetailsService userdetailsevice;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse > createToken( @RequestBody JwtAuthRequest request) throws Exception
	{
		  this.authenticate(request.getUsername(),request.getPassword() ); 
		  UserDetails userDetails=this.userdetailsevice.loadUserByUsername(request.getUsername());
		  String token= this.jwttokenhelper.generateToken(userDetails);
		  
		  JwtAuthResponse response=new JwtAuthResponse();
		  response.setToken(token);
		  return  new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
    UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(username,password);
    try {
    	 this.authenticationManager.authenticate(authtoken);
    }catch(BadCredentialsException e){
    	System.out.println("Invalid username details--------- ");
    	throw new Exception("-----------------Invalid username password-----------");
    	
    }
    
  
	
	} 

}
