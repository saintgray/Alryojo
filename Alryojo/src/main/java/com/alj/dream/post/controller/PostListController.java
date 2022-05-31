package com.alj.dream.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alj.dream.post.service.PostListService;

import security.AccountDetails;

@Controller
public class PostListController {

	@Autowired
	PostListService listService;
	
	@RequestMapping("/post/list")
	public String getPostList(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			Model model,
			Authentication authentication
			) {
		
		AccountDetails logininfo= (AccountDetails)authentication.getPrincipal();
		
		int m_idx = Integer.parseInt(logininfo.getM_idx());
		String wanted = logininfo.getM_type()=="mentor"?"mentee":"mentor";
		
		model.addAttribute("logininfo", logininfo);
		model.addAttribute("listView", listService.getMyPostList(m_idx, wanted, pageNum));
		
		return "post/list";
	}
	
}
