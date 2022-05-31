package com.alj.dream.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.member.domain.EditInfos;
import com.alj.dream.member.service.EditMemberService;

@RestController
@RequestMapping("/member/info/edit")
public class EditMemberController {
	
	private EditMemberService emService;
	
	public EditMemberController() {
	
	}

	@Autowired
	public void setEmService(EditMemberService emService) {
		this.emService = emService;
	}


	@PostMapping
	public int editMember(EditInfos info, Authentication auth, HttpServletRequest req) {
		
		int result=0;
		
		
		

		
		try {
			result=emService.editInfos(info,auth, req );
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}

}
