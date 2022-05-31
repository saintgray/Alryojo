package com.alj.dream.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	
	@RequestMapping("/member/loginform")
	public String showLoginForm(HttpServletRequest req, HttpServletResponse resp) {
		
		
		
		// 권한이 필요한 페이지에 접근하려다가 이 곳으로 리다이렉팅 되지 않았을 때만 (= 사용자가 직접 로그인을 하려고 들어왔을 때만)
		// 캐시를 비운다.
		String intercepted=req.getParameter("intercepted");
		
		if((intercepted==null)){
				RequestCache rc= new HttpSessionRequestCache();
				rc.removeRequest(req,resp);
    	};

		
		return "member/loginform";
	}

}