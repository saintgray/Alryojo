package com.alj.dream.profile.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.file_profile.service.FileProfileRegisterService;
import com.alj.dream.profile.domain.ProfileRegisterData;
import com.alj.dream.profile.service.RegisterProfileService;

import security.AccountDetails;

@Controller
@RequestMapping("/member/profile/register")
public class ProfileRegisterController {

	private FileProfileRegisterService fileServie;
	private RegisterProfileService profileService;
	
	public ProfileRegisterController() {

	}
	
	
	@Autowired
	public ProfileRegisterController(FileProfileRegisterService fileServie, RegisterProfileService profileService) {
		this.fileServie = fileServie;
		this.profileService = profileService;
	}



	@GetMapping
	public String showProfileRegisterForm() {
		return "member/profile/profileregisterform";
	}
	
	@PostMapping
	@ResponseBody
	public String registerProfile(HttpServletRequest req, ProfileRegisterData data, Authentication auth) {
		
		data.setM_idx(((AccountDetails)auth.getPrincipal()).getM_idx());
		
		System.out.println(data.getFiles());
		
		System.out.println(data.getFiles()==null);
		
		
		System.out.println(data.getCareer());
		System.out.println(data.getM_idx());
		System.out.println(data.getLine());
		System.out.println(data.getCalltime());
		
		
	

		int result=0;
		
		
		try {
			result=profileService.insertProfile(data);
			if(result>0) {
				fileServie.insertAttachFile(req, data);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		
		
		return String.valueOf(result);
		
		
	}
	
	
}
