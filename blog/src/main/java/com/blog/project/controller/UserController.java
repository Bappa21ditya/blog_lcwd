package com.blog.project.controller;
import com.blog.project.paylods.*;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.project.services.UserService;

import jakarta.validation.Valid;
//import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
//@Validated
public class UserController {
	@Autowired
	private UserService userservice;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser( @RequestBody @Valid UserDto userdto)
	{
		 UserDto createduserDto=this.userservice.createUser(userdto);
		 return new ResponseEntity<>(createduserDto,HttpStatus.CREATED);
		
	}
	
// update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userdto,@PathVariable("userId") int uid)
	{
	UserDto dt=	this.userservice.updateUser(userdto, uid);
		return ResponseEntity.ok(dt);
	}
	
	
//	@GetMapping("/{id}")
//	public UserDto getuser(@PathVariable("id")int  id)
//	{
//	 UserDto dto=this.userservice.getUserById(id);
//	
//		return dto;
//	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return  ResponseEntity.ok(this.userservice.getAllUsers());
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getsingleuser(@PathVariable("userid") int userid )
	{
		 return ResponseEntity.ok(this.userservice.getUserById(userid));
//		return  ResponseEntity.ok(this.userservice.getAllUsers());
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable("id") int id)
	{
		 this.userservice.deleteUser(id);
//		return new  ResponseEntity(Map.of("message ","user  deleted successfully"),HttpStatus.OK);
		 
		 return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
}
