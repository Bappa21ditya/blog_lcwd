package com.blog.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.blog.project.paylods.ApiResponse;
import com.blog.project.paylods.CategoryDto;
import com.blog.project.paylods.UserDto;
import com.blog.project.services.CtegoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	// create 
	
	@Autowired
	private CtegoryService categoryservice;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categorydto)
	{
		CategoryDto dto= this.categoryservice.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(dto,HttpStatus.CREATED);
		
	}
	
	
	//update
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> upateCategory(@Valid @RequestBody CategoryDto categorydto,@PathVariable Integer catId)
	{
		CategoryDto dto= this.categoryservice.updateCategory(categorydto, catId);
		return new ResponseEntity<CategoryDto>(dto,HttpStatus.OK);
		
	}

	
	
	// delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> upateCategory(@PathVariable Integer catId)
	{
		 this.categoryservice.deltecategory( catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",false),HttpStatus.OK);
		
	}

	
	
	// get 
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto cat=	 this.categoryservice.getCategory( catId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
		
	}
	
	
	
	// get all
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
	List<CategoryDto> cat= this.categoryservice.getCategories();
//		return ResponseEntity<List<CategoryDto>>(cat,HttpStatus.OK);
//		return  ResponseEntity.ok(this.userservice.getAllUsers());
//	return  ResponseEntity.ok(this.categoryservice.getCategories());
	return  ResponseEntity.ok( cat);
		
	}
	
	

}
