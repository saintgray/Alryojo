package com.alj.dream.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/admin/manage")
public class MainPageController {
	
	
	
	@GetMapping
	public String showAdminPage() {
		return "admin/adminpage";
	}
	
}
