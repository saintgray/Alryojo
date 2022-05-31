package com.alj.dream.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.category.service.EditCategoryService;

@RestController
@RequestMapping("/category/edit")
public class CategoryEditController {
	
	
	private EditCategoryService editService;
	
	public CategoryEditController() {

	}
	@Autowired
	public CategoryEditController(EditCategoryService editService) {
		this.editService = editService;
	}
	
	
	@PostMapping
	public int editCategoryName(String name,String idx) {
		
		System.out.println(name);
		System.out.println(idx);
		
		int result=editService.editCategoryName(name,idx);
		return result;
	}
	
	
	
	

}
