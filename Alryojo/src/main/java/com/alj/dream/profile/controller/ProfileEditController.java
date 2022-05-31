package com.alj.dream.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.profile.domain.ProfileRegisterData;
import com.alj.dream.profile.service.ProfileEditService;

import security.AccountDetails;

@RestController
@RequestMapping("/member/profile/edit")
public class ProfileEditController {

	private ProfileEditService editService;
	
	public ProfileEditController() {
	
	}
	
	
	@Autowired
	public ProfileEditController(ProfileEditService editService) {
		this.editService = editService;
	}



	@PostMapping
	public int editProfile(ProfileRegisterData data, Authentication auth) {
		
		
		int result=0;
		
		data.setM_idx(((AccountDetails)auth.getPrincipal()).getM_idx());
		
		
		try {
			
			result=editService.updateProfile(data);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
}
