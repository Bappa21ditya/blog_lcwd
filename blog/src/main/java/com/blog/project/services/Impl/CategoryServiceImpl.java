package com.blog.project.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.project.entities.Category;
import com.blog.project.entities.User;
import com.blog.project.exceptions.ResourceNotFoundException;
import com.blog.project.paylods.CategoryDto;
import com.blog.project.paylods.UserDto;
import com.blog.project.repositories.CategoryRepository;
import com.blog.project.services.CtegoryService;

@Service
public class CategoryServiceImpl implements CtegoryService {

	@Autowired
	private CategoryRepository categoryrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		// TODO Auto-generated method stub
		
	Category cat=	this.modelmapper.map(categorydto, Category.class);
	Category addedcat =this.categoryrepo.save(cat);
	
		return this.modelmapper.map(addedcat,CategoryDto.class);
	}
	
	@Override
	public CategoryDto updateCategory(CategoryDto categorydto,Integer CategoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("category","category Id", CategoryId));
		
		cat.setCategoryTitle(categorydto.getCategoryTitle());
		cat.setCategoryDescription(categorydto.getCategoryDescription());
		Category updatedcat=this.categoryrepo.save(cat);
		return 	this.modelmapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deltecategory(Integer CategoryId) {
		// TODO Auto-generated method stub
		Category c1 = this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("category","category Id", CategoryId));
		this.categoryrepo.delete(c1);
		
	}

	@Override
	public CategoryDto getCategory(Integer CategoryId) {
		// TODO Auto-generated method stub
		Category c1=this.categoryrepo.findById(CategoryId).orElseThrow( ()-> new ResourceNotFoundException("category","category Id", CategoryId));
	return 	this.modelmapper.map(c1,CategoryDto.class);
		
	
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		
	 List<Category> c=this.categoryrepo.findAll();
	  List<CategoryDto> dto=  c.stream().map( (cat) -> this.modelmapper.map(cat, CategoryDto.class) ).collect(Collectors.toList());
		return dto;
	}
	
	
//	@Override
//	public List<UserDto> getAllUsers() {
//		
//	List<User> users =this.userrepo.findAll();
//	List<UserDto> userdtos= users.stream().map( user->this.usettoDto(user)).collect(Collectors.toList());
//		
//	return userdtos;
//	}


}
