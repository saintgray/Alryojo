package com.alj.dream.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.qna.service.QnaContentService;

@Controller
@RequestMapping("/qna/content")
public class QnaContentController {
	
	@Autowired
	private QnaContentService service;
	
	@GetMapping
	public String getQnaContent(String qna_idx, Model model) {
		
		try {
			model.addAttribute("content", service.getQna(qna_idx));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "qna/qnacontent";
		
	}
}
