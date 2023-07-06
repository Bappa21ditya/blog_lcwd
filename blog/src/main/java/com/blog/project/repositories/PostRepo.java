package com.blog.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.project.entities.*;
public interface PostRepo  extends JpaRepository<Post,Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post>findByNameContaining(String name);


}
