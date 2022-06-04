package com.alj.dream.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.member.domain.PageRequest;
import com.alj.dream.member.service.MemberListService;

@Controller

public class MemberListController {
	
	@Autowired
	private MemberListService memberListService;
	
	@RequestMapping("/admin/member/list")
	public String showMemberPage(HttpServletRequest request, HttpServletResponse response, PageRequest pagereq) {
		System.out.println("selectPage>>>");
		System.out.println(pagereq.getSelectPage());
		
		try {
			
			request.setAttribute("member", memberListService.getMemberList(pagereq));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// 응답의 헤더 Cache-Control 속성을 어떠한 요청도 캐시로 저장하지 않도록 설정한다.
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires",0); 
		
		// 요청의 프로코톨 방식이 HTTP/1.1 과 같다면 Cache-Control 속성을 캐시를 저장하되 유효한 캐시인지 요청시마다 서버에 질의한다.
		if (request.getProtocol().equals("HTTP/1.1")) response.setHeader("Cache-Control", "no-cache");
		// HTTP/1.0 프로토콜을 사용하는 클라이언트를 위해 호환성을 위해 Pragma 속성도 no-cache 로 설정한다.
		response.setHeader("Pragma","no-cache"); 
		
		return "admin/member/membermain";
		
	}
	
	@PostMapping("/admin/adminTest")
	@ResponseBody
	public Map<String,Object> test(){
		return new HashMap<String, Object>();
	}
	

}
