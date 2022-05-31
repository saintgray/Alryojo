package com.alj.dream.post.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.post.domain.PostEditRequest;
import com.alj.dream.post.domain.PostWriteRequest;
import com.alj.dream.post.service.PostEditService;
import com.alj.dream.profile.domain.ProfileRequest;

@Controller
@RequestMapping("/post/edit")
public class PostEditController {
	
	@Autowired
	private PostEditService service;
	
	@GetMapping
	public String getEditForm(@RequestParam("idx")int post_idx, Model model) {
		
		PostWriteRequest editRequest = service.getPost(post_idx);
		model.addAttribute("editRequest", editRequest);
		
		System.out.println("editRequest : ");
		System.out.println(editRequest);
		
		ProfileRequest writerProfile = service.getWriterProfile(Integer.parseInt(editRequest.getM_idx()));
		model.addAttribute("writerProfile", writerProfile);
		
		System.out.println("writerProfile : ");
		System.out.println(writerProfile);

		return "post/editform";
	}
	
	@PostMapping
	@ResponseBody
	public Map<String, Integer> editPost(HttpServletRequest request, PostEditRequest wRequest) {		
		
		System.out.println("수정내용 확인 : " + wRequest);
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		String saveDir = "post/attachfiles/";
		
		// 글 수정
		result.put("cnt", service.editPost(wRequest, saveDir));
		result.put("idx", wRequest.getPost_idx());
		
		System.out.println("cnt: " + result.get("cnt"));
		System.out.println("idx: " + result.get("idx"));
		
		return result;
		
	}

}
