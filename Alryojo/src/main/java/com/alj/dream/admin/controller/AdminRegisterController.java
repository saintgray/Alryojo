package com.alj.dream.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.admin.domain.Admin;
import com.alj.dream.admin.service.AdminRegisterService;
import com.alj.dream.member.domain.Emailinfos;

@Controller
@RequestMapping("/superadmin/admin/register")
public class AdminRegisterController {
	
	
	private AdminRegisterService arService;
	
	public AdminRegisterController() {
		
	}
	
	@Autowired
	public AdminRegisterController(AdminRegisterService arService) {
		this.arService = arService;
	}


	@GetMapping
	public String showAdminRegisterForm() {
		
		return "admin/registerform";
	}
	
	@PostMapping
	@ResponseBody
	public int registerAdmin(Emailinfos info, Admin admin) {
		
		int result=0;
		
		result=arService.insertAdmin(info, admin);
		
		return result;
	}
	
}
