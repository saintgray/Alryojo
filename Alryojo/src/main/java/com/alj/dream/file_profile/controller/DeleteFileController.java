package com.alj.dream.file_profile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.file_profile.service.DeleteFileService;

@RestController
@RequestMapping("/member/profile/deletefile")
public class DeleteFileController {
	
	private DeleteFileService delFileService;
	
	public DeleteFileController() {}

	@Autowired
	public DeleteFileController(DeleteFileService delFileService) {
		this.delFileService = delFileService;
	}



	@PostMapping
	public int deleteAttachFile(HttpServletRequest req, String file_nm) {
		
		int result=0;
		
		result=delFileService.deleteFile(req, file_nm);
		
		return result;
	}

}
