package com.blog.project.services.Impl;



import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.util.StreamUtils;
import jakarta.servlet.http.HttpServletResponse;


import javax.naming.ldap.PagedResultsResponseControl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.project.entities.Category;
import com.blog.project.entities.Post;
import com.blog.project.entities.User;
import com.blog.project.exceptions.ResourceNotFoundException;
import com.blog.project.paylods.PostDto;
import com.blog.project.paylods.PostResponse;
import com.blog.project.repositories.CategoryRepository;
import com.blog.project.repositories.PostRepo;
import com.blog.project.repositories.UserRepo;
import com.blog.project.services.PostService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userRepo;
	
	
	
	@Autowired
	private CategoryRepository categoryrepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User u1 =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" ,"id",userId) );
//		User user = this.userRepo.findById(userId).orElseThrow( () ->new  ResourceNotFoundException("user","user id",userId) );
		Category cat= this.categoryrepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category","categoryid",categoryId) ); 
		
	Post p1=this.modelmapper.map(postDto, Post.class);
	    p1.setImagename("default.png");
	    p1.setAddDate(new Date());
	    p1.setUser(u1);
	    p1.setCategory(cat);
	    
	     Post newpost=this.postRepo.save(p1);
	
		return  this.modelmapper.map(newpost, PostDto.class) ;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer PostId) {
		// TODO Auto-generated method stub
		Post p1=this.postRepo.findById(PostId).orElseThrow(()-> new  ResourceNotFoundException("post","post id",PostId));
//		p1.setAddDate(postDto.getAddDate());
		p1.setContent(postDto.getContent());
		p1.setName(postDto.getName());
		p1.setImagename(postDto.getImagename());
		
		Post updatedpost= this.postRepo.save(p1);
		return this.modelmapper.map(updatedpost,PostDto.class);
	}

	@Override
	public void deletePost(Integer PostId) {
	 Post p1=this.postRepo.findById(PostId).orElseThrow( ()-> new ResourceNotFoundException("post","post id",PostId));
		this.postRepo.delete(p1);
	}

	@Override
	public PostDto getPostById(Integer Postid) {
		// TODO Auto-generated method stub
	 Post p1=this.postRepo.findById(Postid).orElseThrow(()-> new ResourceNotFoundException("post","postId",Postid));
	PostDto dto=this.modelmapper.map(p1,PostDto.class);	
	 return dto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String SortDir) {
		
		
//		int pageSize=5;
//		int pageNumber=1;
		
		// ternary operator
		Sort s1=(SortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Sort sort=null;
		
		if(SortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable p= PageRequest.of(pageNumber, pageSize,sort);
		
		// it will return page post
		Page<Post> pagepost=this.postRepo.findAll(p);
		
		List<Post> allpost=pagepost.getContent();
		
		// TODO Auto-generated method stub
//		List<Post> posts=this.postRepo.findAll();
		List<PostDto> p1=allpost.stream().map((x) -> this.modelmapper.map(x,PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse postresponse=new PostResponse();
		postresponse.setContent(p1);
		postresponse.setPageNumber(pagepost.getNumber());
		postresponse.setPageSize(pagepost.getSize());
		postresponse.setTotalElements(pagepost.getTotalElements());
		
		postresponse.setTotalPages(pagepost.getTotalPages());
		postresponse.setTotalPages(pagepost.getTotalPages());
		
		postresponse.setLastPage(pagepost.isLast());
		
		return postresponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		// find the category with id
		Category cat=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryid",categoryId) );
   	// it will give all the post of the category
		List<Post> posts=	this.postRepo.findByCategory(cat);
   	 //convrt post to postdto
		
	List<PostDto>p1=posts.stream().map( (post)-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList()); 
		return p1;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		// fetch user
		User u1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id",userId) ); 
		// get all the post
		List<Post> cat=this.postRepo.findByUser(u1);
		// convert it to dtod
		List<PostDto>p1=	cat.stream().map( (x) -> this.modelmapper.map(x, PostDto.class)).collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return p1;
	}

	@Override
	public List<PostDto> searchByPost(String keyword) {
		// TODO Auto-generated method stub
List<Post>	posts=	this.postRepo.findByNameContaining(keyword);
List<PostDto>	dto=posts.stream().map((post)-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dto;
	}

}
