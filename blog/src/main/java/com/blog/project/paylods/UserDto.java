package com.blog.project.paylods;

//import org.hibernate.validator.constraints.Email;

//import org.hibernate.validator.constraints.NotEmpty;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int user_id;
	
//	@NotBlank
	@NotEmpty
	@Size(min=4,message="username mustbe min of 4 charcters")
	private String name;
	
	@Email(message="email is not valid")
	private String email;
	
	
	@NotEmpty
	@Size(min=3,max=10,message="password must be of 3 chars and max 10 chars")
//	@Ptterns()
	private String password;
	
	@NotEmpty
	private String about;
	

}
