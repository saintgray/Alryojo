package com.alj.dream.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.review.service.ReviewService;

@Controller
public class ReviewController {


	@Autowired
	private ReviewService service;



	

	// 리뷰 등록할때
	@RequestMapping(value="/review/regreview", method=RequestMethod.POST)
	@ResponseBody
	public int regReview(
			@RequestParam("rating") int rating,
			@RequestParam("comment") String comment,
			@RequestParam("matchidx") int matchidx
			) {

		int resultCnt = service.regReview(rating,comment,matchidx);

		return resultCnt;
	}
}

