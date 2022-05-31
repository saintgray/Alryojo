package com.alj.dream.member.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.admin.service.AdminInfoService;
import com.alj.dream.member.service.MyInfoService;

import security.AccountDetails;

@Controller
@RequestMapping("/myinfo")
public class MyInfoController {
	
	
	
	private MyInfoService miService;
	private AdminInfoService aiService;
	
	public MyInfoController() {

	}
	
	@Autowired
	public MyInfoController(MyInfoService miService, AdminInfoService aiService) {
		this.miService = miService;
		this.aiService = aiService;
	}



	@GetMapping
	public String showUserInfo(Authentication auth, Model model) {
		
		
		Iterator<GrantedAuthority> roles= ((AccountDetails)auth.getPrincipal()).getAuthorities().iterator();
		
		boolean member=true;
		
		while(roles.hasNext()) {
			if(roles.next().getAuthority().equals("ROLE_ADMIN")) {
				member=false;
				break;
			}
			
			
		}
		
		
		
		if(member) {
			String principal_idx=((AccountDetails)auth.getPrincipal()).getM_idx();
			
			// 허가증에 본인의 idx, email, photo 가 모두 들어가 있으므로 DB 에서는 그 사람의 관심사만 가져오면 된다.
			model.addAttribute("info", miService.getPrincipalInfos(principal_idx));
		}else {
			String principal_idx=((AccountDetails)auth.getPrincipal()).getAdmin_idx();
			
			model.addAttribute("info",aiService.getAdminInfo(principal_idx));
		}
		
		return "member/myinfo";
	}

	

}
