package com.alj.dream.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.notice.domain.PageRequest;
import com.alj.dream.notice.service.NoticeListService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeListService noticeListService;
	
	

	@GetMapping
	public String showNoticePage(HttpServletRequest request, PageRequest pageReq) {
		//데이터의 처리는 서비스에서
		//System.out.println(model);
		//System.out.println(pageReq);
		
//		List<Notice> list =null;
//		
//		list=noticeListService.getNoticeList();
//		
//		model.addAttribute("notices", list);
		
		
		request.setAttribute("notices", noticeListService.getNoticeList(pageReq));
		//model.addAttribute("notices", );
		
		
		//경로만 반환
		return "notice/noticemain";
	}

	
}
