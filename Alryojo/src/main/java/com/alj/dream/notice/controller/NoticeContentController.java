package com.alj.dream.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.notice.service.NoticeContentService;

@Controller
@RequestMapping("/notice/content")
public class NoticeContentController {
	
	@Autowired
	private NoticeContentService service;
	
	
	
	@GetMapping
	public String getNoticeContent(String notice_idx, Model model) {
		
		System.out.println(notice_idx);
		//////////////
		try {
			model.addAttribute("content", service.getNotice(notice_idx));
		///////////////
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "notice/noticecontent";
	}

}
