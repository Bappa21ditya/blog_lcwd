package com.blog.project.paylods;

//import lombok.Getter;
//import lombok.Setter;



public class CommentDto {
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
	private int id; 
	   private	String contents;
	 
}
