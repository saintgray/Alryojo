package com.alj.dream.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chkAuthKey")
public class CheckAuthKeyController {
	
	
		private BCryptPasswordEncoder encoder;
		
		public CheckAuthKeyController() {}
		
		@Autowired
		public CheckAuthKeyController(BCryptPasswordEncoder encoder) {
			
			this.encoder = encoder;
		}

		@PostMapping
		public String checkEmailAuthKey(HttpServletResponse resp, String insertedKey, @CookieValue(value="authKey", required=false)Cookie cookie) {
	
			String result=null;
			
			
			if(cookie==null) {
				result="인증시간이 만료되었습니다";
			}else {
				if(encoder.matches(insertedKey, cookie.getValue())) {
					result="matched";
					Cookie authCookie = new Cookie("authed", "Y");
					authCookie.setMaxAge(60*60*24);
					resp.addCookie(authCookie);
					
					
				}else {
					result="인증번호가 일치하지 않습니다";
				}
			}
			
			
			return result;
		}

}
