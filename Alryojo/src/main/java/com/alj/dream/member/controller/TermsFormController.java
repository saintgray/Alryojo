package com.alj.dream.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/termsform")
public class TermsFormController {
	
	
	
	@GetMapping
	public String showTermsForm() {
		
		return "member/termsform";
	}

}
