package com.blog.project.services;

import java.util.List;

import com.blog.project.entities.User;
import com.blog.project.paylods.UserDto;

public interface UserService {
	UserDto createUser(UserDto user); 
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	

}
