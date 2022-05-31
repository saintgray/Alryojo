package com.alj.dream.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.member.domain.Emailinfos;
import com.alj.dream.member.service.RegisterCheckDuplicatedEmailService;


@Controller
@RequestMapping("/chk_email")
public class RegisterCheckDuplicatedEmailController {
	
	private RegisterCheckDuplicatedEmailService chkEmailService;
	
	public RegisterCheckDuplicatedEmailController() {

	}
	
	
	@Autowired
	public RegisterCheckDuplicatedEmailController(RegisterCheckDuplicatedEmailService chkEmailService) {
		
		this.chkEmailService = chkEmailService;
	}
	@PostMapping
	@ResponseBody
	public String getExist(Emailinfos info) {
		
		String m_email=info.getM_email_prefix().concat("@".concat(info.getM_email_suffix()));
		
		
		System.out.println("사용자가 적은 이메일은?>>>>"+m_email);
		
		String result = chkEmailService.selectByEMail(m_email);
		System.out.println("result>>>"+result);
		
		return result!=null?"사용중이거나 탈퇴한 아이디입니다":"ABLEREGISTER";
		
	}
	
	

}
