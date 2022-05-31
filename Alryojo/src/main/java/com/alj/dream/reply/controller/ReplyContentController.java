package com.alj.dream.reply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.reply.exception.NotThisUserReply;
import com.alj.dream.reply.service.ReplyContentsService;

@Controller
@RequestMapping("/reply/content")
public class ReplyContentController {
	
	private ReplyContentsService rcService;
	
	public ReplyContentController() {
	
	}
	
	@Autowired
	public ReplyContentController(ReplyContentsService rcService) {
		this.rcService = rcService;
	}






	@GetMapping
	public String getReply(String reply_idx, Authentication auth, Model model)  {
		
		
		
		String url="reply/content";
		
		
		
		try {
			
			model.addAttribute("content",rcService.getContent(reply_idx, auth));
			
			
		}catch(NotThisUserReply e) {
			// 만약 rcSerice 의 getContent 실행 도중  NotThisUserReply 예외가 발생했을 경우 ( = 로그인한 사람이 답변 대상자가 아닌 경우)
			// /access/reject 요청을 받는 컨트롤러로 redirect 시켜서 "접근불가능한 페이지입니다" 화면이 보여지도록 한다.
			url="redirect:/access/reject";
		}
		
		
		return url;
		
	}
}
