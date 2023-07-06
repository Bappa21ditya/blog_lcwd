package com.blog.project.services;

import java.util.List;

import com.blog.project.entities.Category;
import com.blog.project.paylods.CategoryDto;

public interface CtegoryService {
	// create 
	
	CategoryDto createCategory(CategoryDto categorydto);
	
	//update
	CategoryDto updateCategory(CategoryDto categorydto,Integer CategoryId);
	
	// delete
	
	  void deltecategory(Integer CategoryId);
	
	//get
	 CategoryDto getCategory(Integer CategoryId);
	
	
	// getAll

	  List<CategoryDto> getCategories();
	
}
