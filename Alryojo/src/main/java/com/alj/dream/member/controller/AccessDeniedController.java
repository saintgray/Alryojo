package com.alj.dream.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access/reject")
public class AccessDeniedController {

	
	
	@GetMapping
	public String showErrorPage() {
		return "error/error";
	}
}
