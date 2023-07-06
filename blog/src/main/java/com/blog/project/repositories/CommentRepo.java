package com.blog.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.project.entities.*;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
