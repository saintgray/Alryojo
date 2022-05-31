package com.alj.dream.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.category.service.CategoryListService;
import com.alj.dream.post.domain.PostListInfo;
import com.alj.dream.post.domain.SearchParams;
import com.alj.dream.post.service.PostListService;

import security.AccountDetails;

@Controller
public class PostSearchController {

	@Autowired
	private PostListService service;
	
	@Autowired
	private CategoryListService catService;
	
	// 검색
	@GetMapping("/post/search")
	public String searchResult(
			@RequestParam(value = "cat", defaultValue = "0") int cat_idx, 
			@RequestParam(value = "loc", defaultValue = "0") int loc_idx, 
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			Authentication authentication,
			Model model
			) {
		
		AccountDetails logininfo= (AccountDetails)authentication.getPrincipal();
		
		int m_idx = Integer.parseInt(logininfo.getM_idx());
		String wanted = logininfo.getM_type();
		
		if(cat_idx==0) {
			cat_idx = Integer.parseInt(catService.getCategoryIdxByName("취미"));
		}
		
		SearchParams params = new SearchParams(m_idx, wanted, cat_idx, loc_idx);
		params.setPageNum(pageNum);
		System.out.println(params);
		
		model.addAttribute("listView", service.getSearchResult(params));
		model.addAttribute("cat", cat_idx);
		model.addAttribute("loc", loc_idx);

		return "post/search";
	}

	// 추천 멘토/멘티
	@PostMapping("/post/recommend")
	@ResponseBody
	public List<PostListInfo> getRecommend(String m_idx, String wanted){
		
		System.out.println("m_idx: " + m_idx);
		System.out.println("wanted: " + wanted);
		
		return service.getRecommend(Integer.parseInt(m_idx), wanted);

	}
	
}
