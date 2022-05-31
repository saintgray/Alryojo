package com.alj.dream.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.admin.service.AdminEditService;
import com.alj.dream.member.domain.EditInfos;

@RestController
@RequestMapping("/admin/info/edit")
public class AdminEditController {

	
	private AdminEditService eaService;
	
	public AdminEditController() {
		
	}
	
	
	@Autowired
	public AdminEditController(AdminEditService eaService) {
		this.eaService = eaService;
	}





	@PostMapping
	public int editAdminInfo(EditInfos info, Authentication auth, HttpServletRequest req) {
		
		int result=0;
		
		
		System.out.println(info.getM_nm());
		System.out.println(info.getPhoto());
		System.out.println(info.getM_password());

		
		try {
			result=eaService.editInfos(info,auth, req);
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}
}
