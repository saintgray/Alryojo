package com.alj.dream.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.notice.domain.NoticeContent;
import com.alj.dream.notice.domain.NoticeRegisterData;
import com.alj.dream.notice.service.NoticeEditService;

@Controller
@RequestMapping("/admin/notice/edit")
public class NoticeEditController {

	@Autowired
	private NoticeEditService editService;
	
	@GetMapping
	public String NoticeEditPage( Model model,String notice_idx) {
		
		// 1. 파라미터로 부터 필요한 정보를 얻는다. 
		// 2. DB에 접근한다.
		// 3. 쿼리를 날린다.
		// 4. 결과를 받는다.
		// 5. 받은결과를 보여줄 화면으로 전달한다. 
		
		
		System.out.println(notice_idx);
		
		NoticeContent noticeContent = editService.getNotice(notice_idx);
		model.addAttribute("noticeContent", noticeContent);
		
		return "notice/noticeeditform";
	}
	
	@PostMapping
	@ResponseBody
	public int updateNotice(Authentication auth, NoticeRegisterData data) {
		
		
		int result=0;
		
		try {
		  result=editService.updateNotice(auth, data);
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		
		return result;
	}
}
