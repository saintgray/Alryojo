package com.alj.dream.file_post.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.file_post.service.PostFileDeleteService;

@Controller
public class PostFileDeleteController {
	
	@Autowired
	PostFileDeleteService delService;
	
	@PostMapping("/post/deleteFile")
	@ResponseBody
	public String deleteFile(HttpServletRequest request, String file_nm, String file_exet) {
		
//		String saveDir = request.getSession().getServletContext().getRealPath("/resources/files/post/attachfiles");
		String saveDir = "post/attachfiles/";
		String filename = file_nm + '.' + file_exet;
		
		System.out.println("삭제할 파일 : " + filename);
		
		delService.deletePostFile(saveDir, filename);
		
		return "deleted";
	}

}
