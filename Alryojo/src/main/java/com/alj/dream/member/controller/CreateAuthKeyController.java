package com.alj.dream.member.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.member.domain.Emailinfos;
import com.alj.dream.util.auth.KeyUtils;
import com.alj.dream.util.mail.EMailUtil;

@RestController
@RequestMapping("/sendAuthKey")
public class CreateAuthKeyController {
	
	private JavaMailSender ms;
	private BCryptPasswordEncoder pwencoder;
	
	public CreateAuthKeyController() {}
	

	@Autowired
	public CreateAuthKeyController(JavaMailSender ms, BCryptPasswordEncoder pwencoder) {
		
		this.ms = ms;
		this.pwencoder = pwencoder;
	}






	// 급하게 만드느라 데이터 처리를 서비스클래스에서 하지 못함
	// 나중에 mail 패키지 안에 따로 서비스 클래스를 만들어서 처리해야 할 듯 합니다.
	@PostMapping
	public String sendAuthKey(HttpServletResponse response, Emailinfos email) {
		
		String result=null;
		String userEmail= EMailUtil.getUserEmail(email);
		
		MimeMessage mm=ms.createMimeMessage();
		
		try {
			MimeMessageHelper mh = new MimeMessageHelper(mm, true,"utf-8");
			
			mh.setSubject("[알려드림] 이메일 인증 Key 를 확인하세요");
			
			String content=null;
			content="<h1>인증용 이메일 key</h1>";
			String authKey=KeyUtils.createAuthKey();
			content+="<h3>".concat(authKey).concat("</h3>");
			mh.setText(content, true);
			
			mh.setTo(userEmail);
			
			ms.send(mm);
			
			result="COMPLETE";
			
			
			Cookie cookie= new Cookie("authKey", pwencoder.encode(authKey));
			cookie.setMaxAge(60*3);
			Cookie authCookie= new Cookie("authed", "N");
			authCookie.setMaxAge(60*3);
			response.addCookie(cookie);
			response.addCookie(authCookie);
			
			
			
			
			
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return result==null?"ERROR":"COMPLETE";
	}
}
