package com.alj.dream.request.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.request.domain.RequestGroup;
import com.alj.dream.request.service.RequestGroupService;

@Controller
public class RequestGroupContorller {

	@Autowired
	private RequestGroupService service;
	//받은 요청 페이지에 들어갈 그룹을 가져온다.
	
	@RequestMapping("/post/requestgroup")
	public String getGroupPage(Model model,
			Authentication auth) {
		
		
		try {
		List<RequestGroup> rg= service.getRequestGroup(auth);
		model.addAttribute("requestGroup", rg);	
		System.out.println("requestGroup : "+rg);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "post/requestgroup";
	}
		
	
	// 매칭업데이트(멤버 삭제 눌렀을 때)
	@RequestMapping(value="/post/matchupdate", method=RequestMethod.POST)
	@ResponseBody
	public int updateMatchYN(
			@RequestParam("matchYN") String matchYN,
			@RequestParam("matchidx") int matchidx
			) {
		System.out.println("ChatRoomController : updateMatchYN 진입");
		
		int result = service.updateMatchYN(matchYN, matchidx);
		
		return result;
	}
}
