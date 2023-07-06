package com.blog.project.services.Impl;
import com.blog.project.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.project.entities.User;
import com.blog.project.paylods.UserDto;
import com.blog.project.repositories.UserRepo;
import com.blog.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		
		User u1=this.dtotouser(userdto);
		
	 User saveuser=	this.userrepo.save(u1);
		return this.usettoDto(saveuser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		
		
		User u1 =this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId) );
		
		u1.setName(userdto.getName());
		u1.setEmail(userdto.getEmail());
		u1.setPassword(userdto.getPassword());
		u1.setAbout(userdto.getAbout());
		
		User updateduser= this.userrepo.save(u1);
		UserDto dto1=this.usettoDto(updateduser);
		return dto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User u2=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId) );
		// change it to dto
		
		return this.usettoDto(u2);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
	List<User> users =this.userrepo.findAll();
	List<UserDto> userdtos= users.stream().map( user->this.usettoDto(user)).collect(Collectors.toList());
		
	return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
	 User u1=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId) );
    this.userrepo.delete(u1);
	}
	
	private User dtotouser(UserDto dto)
	{
		
		User user =this.modelmapper.map(dto, User.class);
//		User user =new User();
//		user.setId(dto.getId());
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setAbout(dto.getAbout());
		
		
		return user;
		
	}
	public UserDto usettoDto(User user)
	{
		
		UserDto dto=this.modelmapper.map(user, UserDto.class);
//		UserDto dto=new UserDto();
//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setPassword(user.getPassword());
//		dto.setAbout(user.getAbout());
		return dto;
	}

}
