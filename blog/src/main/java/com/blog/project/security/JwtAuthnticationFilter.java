package com.blog.project.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;




import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthnticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userdetailservice;
	
	@Autowired
	private  JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// get token
		 String requestTokenHeader=request.getHeader("Authorization");
		 System.out.println("token is --");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken =null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer"))
		{
			jwtToken =requestTokenHeader.substring(7);
			
			try {
				username=this.jwtTokenHelper.getUsernameFromToken(jwtToken);
//			username = this.jwtutill.extractUsername(jwtToken);
			}catch(ExpiredJwtException e)
			{
				e.printStackTrace();
				System.out.println("jwt Token has expired");
			}
		 catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
		 }
			catch(MalformedJwtException e)
			{
//				e.printStackTrace();
				System.out.println("invalid expired");
			}
		}
		else
		{
			System.out.println("jwt Token not starts with bearer string");
		}
		
		// validate 
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
//		 	UserDetails userDetails=  this.userDetailsServiceImpl.loadUserByUsername(username);
			UserDetails userDetails=  this.userdetailservice.loadUserByUsername(username);
			
		 	if(this.jwtTokenHelper.validateToken(jwtToken, userDetails))
		 	{
		 		UsernamePasswordAuthenticationToken usernamePasswordAuthentication =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		 		// token is validate
		 		usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		 		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
		 	}
		 	else
		 	{
		 		System.out.println("Token is not valid");
		 	}
		}
		else
		{
			System.out.println("user name is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
	}
		
		
		
	}


