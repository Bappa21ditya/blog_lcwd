package com.blog.project.services;

import com.blog.project.entities.Comment;
import com.blog.project.paylods.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto comment,Integer postId);
	void deleteComment(Integer commentId); 

}
