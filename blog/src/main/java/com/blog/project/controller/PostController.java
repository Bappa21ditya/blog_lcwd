package com.blog.project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.project.services.*;

import jakarta.servlet.http.HttpServletResponse;

import com.blog.project.paylods.ApiResponse;
import com.blog.project.paylods.PostDto;
import com.blog.project.paylods.PostResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileservice;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts") 
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto ,@PathVariable Integer userId
		,	@PathVariable Integer categoryId
			)
	{
		PostDto createpost=this.postservice.createPost(postdto, userId, categoryId);
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
	}
	// get by user
	
// we can send postdtos  but we want to send response along with results	
	@GetMapping("/user/{userId}/posts")
	public  List<PostDto> getPostByUser( @PathVariable Integer userId)
	{		
		return this.postservice.getPostByUser(userId);
	}
//	
//	@GetMapping("/user/{userId}/posts")
//	public  ResponseEntity<List<PostDto>> getPostByUser( @PathVariable Integer userId)
//	{
//		// we can send postdtos  but we want to send response along with results
//		
//     List<PostDto> posts=this.postservice.getPostByUser(userId);
//      return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
//	}
	
	// get user by category
	@GetMapping("/category/{categoryId}/posts")
	public  ResponseEntity<List<PostDto>> getPostByCategory( @PathVariable Integer categoryId)
	{
		// we can send postdtos  but we want to send response along with results
		
     List<PostDto> posts=this.postservice.getPostByCategory(categoryId);
      return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	// get user by post Id
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getpostbypostid(@PathVariable Integer postId)
	{
		PostDto dto=this.postservice.getPostById(postId);
		return new  ResponseEntity<PostDto>(dto,HttpStatus.OK);
	}
	
	// get all post
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>getallpost(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="postId",required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required=false) String sortDir

			)
	{
		PostResponse p1=this.postservice.getAllPost(pageNumber,pageSize, sortBy,sortDir);
		return new ResponseEntity<PostResponse>(p1,HttpStatus.OK);
	}
	
	// update post
	@PutMapping("/post/{PostId}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postdto , @PathVariable Integer PostId)
	{
		PostDto p1=this.postservice.updatePost(postdto, PostId);
		return new ResponseEntity<PostDto>(p1,HttpStatus.OK);
	}
	
	
	
	// deletepost
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletepost(@PathVariable Integer postId)
	{
		this.postservice.deletePost(postId);
//		return new  ApiResponse("post is successfully deleted ",true);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted successfully",false),HttpStatus.OK);
	}
//	public ResponseEntity<ApiResponse> upateCategory(@PathVariable Integer catId)
//	{
//		 this.categoryservice.deltecategory( catId);
//		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",false),HttpStatus.OK);
//		
//	}
	
	
	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByname(@PathVariable("keyword") String keyword)
	{
		List<PostDto>dto=this.postservice.searchByPost(keyword);
		return new ResponseEntity<List<PostDto>>(dto,HttpStatus.OK);
	}
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadpostImage( @RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException
	{
	PostDto dto= this.postservice.getPostById(postId);

	String Filename=this.fileservice.uploadImage(path, image);
	dto.setImagename(Filename);
	PostDto updatedpost= this.postservice.updatePost(dto, postId);
	return new ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);
	}
	
	// method to serve file
			@GetMapping(value="post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
			public void downloadImage(@PathVariable("imageName") String ImageName,
					HttpServletResponse response) throws IOException
			{
			 InputStream resources=this.fileservice.getResource(path, ImageName);
			 response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			 StreamUtils.copy(resources, response.getOutputStream());
			}

	

}
