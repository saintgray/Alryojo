package com.alj.dream.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.notice.service.NoticeDeleteService;

@RestController
@RequestMapping("/admin/notice/delete")
public class NoticeDeleteController {
	
	@Autowired
	private NoticeDeleteService noticedeleteService;
	
	@PostMapping
	public int NoticeDeletePage(String notice_idx, HttpServletRequest req) {
		
		int result=0;
		
		try {
			result= noticedeleteService.deleteNotice(notice_idx, req);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
