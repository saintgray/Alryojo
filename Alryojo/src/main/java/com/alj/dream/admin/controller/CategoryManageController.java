package com.alj.dream.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categorymanage")
public class CategoryManageController {

	
	
	@GetMapping
	public String showCategoryManagePage() {
		return "admin/category/categorymanage";
	}
}
