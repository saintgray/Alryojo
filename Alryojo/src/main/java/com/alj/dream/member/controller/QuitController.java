package com.alj.dream.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.member.service.QuitService;

@Controller
@RequestMapping("/member/quit")
public class QuitController {
	
	private QuitService quitService;
	
	public QuitController() {
		
	}
	@Autowired
	public QuitController(QuitService quitService) {
		this.quitService = quitService;
	}
	
	@GetMapping
	public String showQuitForm() {
		
		return "member/quitform";
	}
	
	@PostMapping
	@ResponseBody
	public int quitMember(HttpServletRequest req,Authentication auth) {
		
		
		int result=0;
		
		try {
			result=quitService.QuitMember(req, auth);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
				
		
	}
	
	
	
	
  
}
