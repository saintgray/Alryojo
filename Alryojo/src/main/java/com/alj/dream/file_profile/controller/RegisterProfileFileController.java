package com.alj.dream.file_profile.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.file_profile.service.FileProfileRegisterService;
import com.alj.dream.profile.domain.ProfileRegisterData;

@RestController
@RequestMapping("/member/profile/uploadfiles")
public class RegisterProfileFileController {
	
	private FileProfileRegisterService frService;
	
	
	public RegisterProfileFileController() {

	}
	
	@Autowired
	public RegisterProfileFileController(FileProfileRegisterService frService) {
		this.frService = frService;
	}

	
	



	@PostMapping
	public int registerFile(HttpServletRequest req, ProfileRegisterData data) {
		System.out.println(data.getFiles());
		System.out.println(data.getProfile_idx());
		int result=0;
		
		try {
			result=frService.insertAttachFile(req, data);
		}catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		return result;
	}

	
	
	
}
