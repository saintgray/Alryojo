package com.alj.dream.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.qna.domain.PageRequest;
import com.alj.dream.qna.service.QnaListService;

@Controller
@RequestMapping("/qna/list")
public class QnaListController {
	
	private QnaListService listService;
	
	public QnaListController() {
		
	}
	@Autowired
	public QnaListController(QnaListService listService) {
		this.listService = listService;
	}
	
	@GetMapping
	public String getQnaList(PageRequest pageReq, Model model, Authentication auth) {
		
		
		System.out.println(pageReq.getCurPageIndex());
		System.out.println(pageReq.getNumOfPagePerPage());
		System.out.println(pageReq.getNumOfQnaPerPage());
		System.out.println(pageReq.getSelectPage());
		
		
		try {
			model.addAttribute("pageView", listService.getQnaList(pageReq, auth));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "qna/qnalist";
		
	}
	

}
