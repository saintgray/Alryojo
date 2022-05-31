package com.alj.dream.post.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.post.domain.PostWriteRequest;
import com.alj.dream.post.service.PostWriteService;
import com.alj.dream.profile.domain.MyProfileInfo;
import com.alj.dream.profile.domain.ProfileRequest;

import security.AccountDetails;

@Controller
public class PostWriteController {

	@Autowired
	private PostWriteService writeService;

	@GetMapping("/post/write")
	public String getWriteForm(Authentication authentication, Model model) {
		
		AccountDetails logininfo= (AccountDetails)authentication.getPrincipal();
		int m_idx = Integer.parseInt(logininfo.getM_idx());
		
		ProfileRequest writerProfile = writeService.getWriterProfile(m_idx);
		
		if(writerProfile.getLine()==null) {
			writerProfile=null;
		}
		
		model.addAttribute("writerProfile", writerProfile);

		return "post/writeform";
	}

	@PostMapping("/post/write")
	@ResponseBody
	public Map<String, Integer> writePost(HttpServletRequest request, PostWriteRequest wRequest) {

		Map<String, Integer> result = new HashMap<String, Integer>();

		// 글 업로드
		int resultCnt = writeService.writePost(wRequest);

		result.put("cnt", resultCnt);
		result.put("idx", wRequest.getPost_idx());

		return result;
		
	}

	@PostMapping("/post/checkProfile")
	@ResponseBody
	public MyProfileInfo getProfile(Authentication auth) {
		String m_idx=((AccountDetails)auth.getPrincipal()).getM_idx();
		MyProfileInfo info=writeService.getProfile(m_idx);
		
		return info;

	}

}
