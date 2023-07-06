package com.blog.project.services;

import java.util.List;


import com.blog.project.entities.Post;
import com.blog.project.paylods.PostDto;
import com.blog.project.paylods.PostResponse;

public interface PostService {

	
//	 Post createPost()
	
	// create 
	PostDto createPost(PostDto postDto ,Integer userId,Integer categoryId);
	
	// update
	 PostDto updatePost(PostDto postDto,Integer PostId);
	
	// delete
	 void deletePost(Integer PostId);
	
	
	// get
 PostDto getPostById(Integer Postid);
	
	// getall
	 
 PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	 
	 
	// get post by category
	 
	 List<PostDto> getPostByCategory(Integer categoryaId);
	 
	// get all post by user
	 
	 List<PostDto> getPostByUser(Integer userId);
	 
	 // search
	 
	 List<PostDto> searchByPost(String keyword);
	 
	 
	 
}
