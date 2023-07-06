package com.blog.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.project.entities.Comment;
import com.blog.project.paylods.ApiResponse;
import com.blog.project.paylods.CommentDto;
import com.blog.project.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId)
	{
		CommentDto dto=this.commentservice.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(dto,HttpStatus.CREATED);
	}
	@DeleteMapping("/post/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
	   this.commentservice.deleteComment(commentId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully",false),HttpStatus.OK);
	}


}
