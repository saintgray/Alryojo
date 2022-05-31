package com.alj.dream.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.category.domain.CategoryInfo;
import com.alj.dream.category.domain.CategoryRequested;
import com.alj.dream.category.domain.CategorySiblingsInfo;
import com.alj.dream.category.domain.ChildCategory;
import com.alj.dream.category.service.CategoryListService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	
	private CategoryListService listService;
	
	
	public CategoryController() {
		
	}
	
	@Autowired
	public CategoryController(CategoryListService listService) {
		this.listService = listService;
	}


	@GetMapping("/list")
	@ResponseBody
	public List<ChildCategory> getChildCategory(CategoryRequested cat_request) {
		
		
		
		
		List<ChildCategory> result=null;
		result= listService.getList(cat_request.getInterest());
	
		
		return 	result;
			
	}
	
	@GetMapping("/siblings")
	@ResponseBody
	public CategorySiblingsInfo getCategoryByDepth(@RequestParam("idx") String idx){
		
		
		
		CategorySiblingsInfo result=null;
		
		result=listService.getSiblings(idx);
		
		return result;
		
	}
	
	@GetMapping("/all")
	@ResponseBody
	public List<CategoryInfo> getAllCategories(){
		
		List<CategoryInfo> list = null;
		list=listService.selectAll();
		return list;
		
	}
	
	
	@GetMapping("/idx")
	@ResponseBody
	public String getCategoryIdxByName(String name) {
		return listService.getCategoryIdxByName(name);
	}
	

}
