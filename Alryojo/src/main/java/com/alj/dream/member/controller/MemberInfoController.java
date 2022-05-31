package com.alj.dream.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.member.service.MemberInfoService;

@Controller
@RequestMapping("/admin/member/info")
public class MemberInfoController {
	
	@Autowired
	private MemberInfoService service;
	
	@GetMapping
	public String getMemberInfo(String m_idx, Model model) {
		
		System.out.println(m_idx);
		
		try {
		model.addAttribute("memberinfo", service.getMemberInfo(m_idx));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "admin/member/memberinfo";
	}
}
	
	