package com.alj.dream.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.profile.service.DeleteProfileService;

@RestController
@RequestMapping("/member/profile/delete")
public class DeleteProfileController {
	
	private DeleteProfileService delService;
	
	public DeleteProfileController() {

	}
	
	
	@Autowired
	public DeleteProfileController(DeleteProfileService delService) {
		this.delService = delService;
	}


	@PostMapping
	public int deleteProfile(String profile_idx) {
		
		int result =0;
		
		try {
			
			result=delService.deleteProfile(profile_idx);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	

}
