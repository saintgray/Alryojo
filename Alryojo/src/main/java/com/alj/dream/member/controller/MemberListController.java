package com.alj.dream.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.member.domain.MemberListView;
import com.alj.dream.member.domain.PageRequest;
import com.alj.dream.member.service.MemberListService;

@RestController
public class MemberListController {
	
	@Autowired
	private MemberListService memberListService;
	
	@GetMapping("/admin/members")
	public ResponseEntity<?> showMemberPage(HttpServletRequest request, HttpServletResponse response, PageRequest pagereq) {
		
		
		ResponseEntity<?> result=null;
		try {
			MemberListView memberListView=memberListService.getMemberList(pagereq);
			if(memberListView !=null) {
				result= ResponseEntity.ok(memberListView);
			}else {
				result = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		}catch(Exception e) {
			result=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return  result;
		
	}
	
	@PostMapping("/admin/adminTest")
	@ResponseBody
	public Map<String,Object> test(){
		return new HashMap<String, Object>();
	}
	

}
