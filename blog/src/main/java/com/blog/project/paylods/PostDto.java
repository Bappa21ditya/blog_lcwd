package com.blog.project.paylods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.project.entities.Category;
import com.blog.project.entities.Comment;
import com.blog.project.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	

	private Integer postId;
	
	private String name;
	
	private String content;
	
	private String imagename;
	
	private Date addDate;
	
	private CategoryDto category;
	
	
	private UserDto user;
	
	private List<CommentDto> comments=new ArrayList<>();
//	private List<Comment> comments=new ArrayList<>();
	
//	private List<Comment> posts=new ArrayList<>();



}
