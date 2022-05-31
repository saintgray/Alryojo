package com.alj.dream.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.category.service.DeleteCategoryService;

@RestController
@RequestMapping("/category/delete")
public class CategoryDeleteController {
	
	
	private DeleteCategoryService delService;
	
	public CategoryDeleteController() {

	}
	@Autowired
	public CategoryDeleteController(DeleteCategoryService delService) {
		this.delService = delService;
	}
	
	
	@PostMapping
	public int deleteCategory(String idx) {
		
		
		System.out.println("idx>>".concat(idx));
		int result= delService.deleteCategory(idx);
		
		return result;
	}
	
	

}
