package com.alj.dream.member.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.member.service.FindAccountService;
import com.alj.dream.util.auth.KeyUtils;

@Controller
@RequestMapping("/findaccount")
public class FindAccountController {

	
	private JavaMailSender ms;
	private FindAccountService faService;
	private BCryptPasswordEncoder encoder;
	
	public FindAccountController() {
		
	}
	
	
	
	@Autowired
	public FindAccountController(JavaMailSender ms, FindAccountService faService, BCryptPasswordEncoder encoder) {
		this.ms = ms;
		this.faService = faService;
		this.encoder = encoder;
	}








	@GetMapping
	public String showFindMeForm() {
		
		return "member/findaccountform";
	}
	
	@PostMapping
	@ResponseBody
	public int sendTempCode(HttpServletRequest req, String email) {
		
		
		int result=0;
		
		
		MimeMessage mm=ms.createMimeMessage();
		//MimeMessageHelper mh = new MimeMessageHelper(mimeMessage, multipart, encoding)
		String tempCode=encoder.encode(KeyUtils.createRandomKeyCode());
		
		System.out.println("계정명: ".concat(email));
		String reqUrl="http://".concat(req.getHeader("host")).concat(req.getContextPath().concat("/resettingpassword?keycode=").concat(tempCode));
		//String reqUrl=req.getContextPath().concat("/resettingpassword?keycode=").concat(tempCode);
		System.out.println(tempCode);
		System.out.println(reqUrl);
		
		try {
			
			
			result=faService.updateTempCode(tempCode, email);
			
			
			MimeMessageHelper mh = new MimeMessageHelper(mm, false, "UTF-8");
			

			// 1.메일 제목
			mh.setSubject("[알려드림] 비밀번호 변경 설정");
			
			String text="<div style=\"margin:0 auto; border:1px solid #FCA106; min-width:100% \">";
			text+="<h1 style=\"text-align=center; margin-bottom:100px;\">비밀번호 변경 안내</h1>";
			text+="<button type=\"button\" style=\"background-color: #142B6F; border: 0; border-radius: 15px; outline: 2px solid black;\">";
			text+="<a target=\"_blank\" href=\"";
			text+=reqUrl;
			text+="\" style=\"font-size:35px; font-weight: bold; color: rgb(249,249,249); text-decoration: none; padding:5px\">비밀번호 재설정</a>";
			text+="</button>";
			
			text+="</div>";
			
			mh.setText(text, true);
			
			mh.setTo(email);
			
			ms.send(mm);
			
			
		} catch (MessagingException e) {
			faService.updateTempCode(null, email);
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return result;
	}
	
	
	
	
	
	
	
	
}
