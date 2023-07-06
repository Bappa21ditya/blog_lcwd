package com.blog.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blog.project.entities.User;
import com.blog.project.exceptions.ResourceNotFoundException;
import com.blog.project.repositories.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private  UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		// loading user from data base by user name;
	User u1=this.userrepo.findByEmail(username).orElseThrow( ()-> new ResourceNotFoundException("user","email :"+username,0 ));
		return u1;
	}

}
