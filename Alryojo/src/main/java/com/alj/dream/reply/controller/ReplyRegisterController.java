package com.alj.dream.reply.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.reply.domain.ReplyRegisterData;
import com.alj.dream.reply.service.ReplyRegisterService;

@Controller
@RequestMapping("/admin/reply/register")
public class ReplyRegisterController {
	
	private ReplyRegisterService rrService;
	
	
	public ReplyRegisterController() {

	}

	@Autowired
	public ReplyRegisterController(ReplyRegisterService rrService) {
		this.rrService = rrService;
	}
	
	@GetMapping
	public String showReplyRegForm(HttpServletRequest req) {
		
		
		
		return "admin/reply/registerform";
	}	
	
	@PostMapping
	@ResponseBody
	public int registerReply(ReplyRegisterData data, Authentication auth) {
		int result =0;
		
		try {
			result=rrService.registerReply(data,auth);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	

}
