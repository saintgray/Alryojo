package com.alj.dream.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import security.AccountDetails;

// 멘토/멘티 전환 요청을 처리하는 SwitchTypeController
@Controller
@RequestMapping("/member/switchType")
public class SwitchTypeController {
	
	@GetMapping
	public void switchType(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
	
		
		
		// 유저의 허가증 객체를 받아 멘토일시 멘티로 멘티일시 멘토로 변경한다.
		AccountDetails logininfo=(AccountDetails)authentication.getPrincipal();
	

		
		logininfo.setM_type(logininfo.getM_type().equals("mentee")?"mentor":"mentee");
		
		// 보던 페이지로 리다이렉트
		// 단 채팅방에서 타입을 변환시 채팅 리스트로 리다이렉트한다.
		if(req.getHeader("referer").matches("(.*)chat/chatroom(.*)")) {
			resp.sendRedirect(req.getContextPath().concat("/chat/chatlist"));
		}else {
			resp.sendRedirect(req.getHeader("referer"));
		}
		
		
		
		
	}
	

}
