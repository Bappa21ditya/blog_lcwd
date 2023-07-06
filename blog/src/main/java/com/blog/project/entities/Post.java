package com.blog.project.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.blog.project.entities.*;

@Entity
@Table(name="post")
@Setter
@Getter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_titile",length=100,nullable=false)
	private String name;
	
	@Column(length=1000)
	private String content;
	
	private String imagename;
	
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	// forien key at comment
//	private Set<Comment> comments=new HashSet<>();
	@OneToMany(mappedBy="post" ,cascade=CascadeType.ALL)
	private List<Comment> comments=new ArrayList<>();
	
	

}
