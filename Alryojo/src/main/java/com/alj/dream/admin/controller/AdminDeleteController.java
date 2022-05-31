package com.alj.dream.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/delete")
public class AdminDeleteController {

	@PostMapping
	public int deleteAdmin() {
		
		
		return 0;
	}
}
