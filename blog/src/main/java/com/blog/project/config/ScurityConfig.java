package com.blog.project.config;

import com.blog.project.security.CustomUserDetailService;
import com.blog.project.security.JwtAuthenticationEntryPoint;
import com.blog.project.security.JwtAuthnticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.*;
import java.util.*;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ScurityConfig  {
	
	@Autowired
	private CustomUserDetailService customerUserDtailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthnticationFilter jwtAuthnticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		   http.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/v1/auth/login")
			.permitAll()
			.requestMatchers(HttpMethod.OPTIONS)
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		   http.addFilterBefore(this.jwtAuthnticationFilter,  UsernamePasswordAuthenticationFilter.class);
			
			
		   

//			   
			   http.authenticationProvider(  daoAuthenticationProvider());
			   DefaultSecurityFilterChain build=http.build();
			   return build;
	}
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//
//	{
//		auth.userDetailsService(this.customerUserDtailService).passwordEncoder(passwordEncoder());	
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
//		return new BCryptPasswordEncoder();
		return  NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception
	{
	 return  configuration.getAuthenticationManager();
	}
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customerUserDtailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
}
