package com.alj.dream.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.category.domain.Category;
import com.alj.dream.category.service.RegisterCategoryService;

@RestController
@RequestMapping("/category/register")
public class CategoryRegisterController {
	
	
	private RegisterCategoryService regService;
	
	public CategoryRegisterController() {

	}
	
	
	
	@Autowired
	public CategoryRegisterController(RegisterCategoryService regService) {
		this.regService = regService;
	}




	@PostMapping
	public Category registerCategory(@RequestBody Category data) {

		
		Category result=null;

		try {
		  result=regService.register(data);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
