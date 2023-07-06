package com.blog.project.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
   private	String contents;
 
  @ManyToOne
  private Post post;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getContents() {
	return contents;
}

public void setContents(String contents) {
	this.contents = contents;
}

public Post getPost() {
	return post;
}

public void setPost(Post post) {
	this.post = post;
}



 

}
