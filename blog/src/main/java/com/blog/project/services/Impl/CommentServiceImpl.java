package com.blog.project.services.Impl;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.project.entities.Comment;
import com.blog.project.entities.Post;
import com.blog.project.paylods.CommentDto;
import com.blog.project.repositories.CommentRepo;
import com.blog.project.repositories.PostRepo;
import com.blog.project.services.CommentService;
import com.blog.project.exceptions.*;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CommentDto createComment(CommentDto dto, Integer postId) {
			Post post=this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId", postId));
			Comment comment=this.modelmapper.map(dto, Comment.class);
//			comment.setContents(dto.getContents());
			comment.setPost(post);
			
//			comment.setContent(null);
			Comment savedcomment=this.commentrepo.save(comment);
			return this.modelmapper.map(savedcomment, CommentDto.class);	
			
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","commentId", commentId));
        this.commentrepo.delete(com);
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
