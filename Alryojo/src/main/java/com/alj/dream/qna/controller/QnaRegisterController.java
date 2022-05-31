package com.alj.dream.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.qna.domain.QnaRegisterData;
import com.alj.dream.qna.service.QnaRegisterService;

@Controller
@RequestMapping("/member/qna/register")
public class QnaRegisterController {
	
	private QnaRegisterService qrService;
	
	
	public QnaRegisterController() {

	}

	@Autowired
	public QnaRegisterController(QnaRegisterService qrService) {
		this.qrService = qrService;
	}
	
	
	@GetMapping
	public String showQnaRegisterForm() {
		
		
		return "qna/qnaregisterform";
	}
	
	@PostMapping
	@ResponseBody
	public int insertQna(Authentication auth, QnaRegisterData data) {
		
		int result=0;
		
		try {
			
			result=qrService.insertQna(auth,data);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
		
	}
	
	
	
	

}
