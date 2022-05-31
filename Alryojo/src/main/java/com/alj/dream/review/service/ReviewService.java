package com.alj.dream.review.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.review.dao.ReviewDao;

@Service
public class ReviewService {

	private ReviewDao dao;

	@Autowired 
	private SqlSessionTemplate template;

	// 리뷰 등록할 때
	public int regReview(int rating, String comment, int matchidx) {


		dao = template.getMapper(ReviewDao.class);

		int resultCnt = dao.insertReview(rating, comment, matchidx);

		return resultCnt;
	}

}

