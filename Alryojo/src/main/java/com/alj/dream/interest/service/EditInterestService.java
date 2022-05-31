package com.alj.dream.interest.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.interest.dao.InterestDao;


@Service
public class EditInterestService {
	
	private SqlSessionTemplate sst;
	
	public EditInterestService() {
	
	}
	
	@Autowired
	public EditInterestService(SqlSessionTemplate sst) {
		this.sst = sst;
	}


	@Transactional
	public int editInterest(String principal_idx,List<String> interest) {
		
		int result=0;
		
		
		// 1. DB에 있는 이 회원의 관심분야를 모두 지운다.
		sst.getMapper(InterestDao.class).deleteAllInterest(principal_idx);
		
		
		// 2. 새로 선택한 관심분야를 DB에 추가한다.
		
		result=sst.getMapper(InterestDao.class).registerUserInterest(interest, principal_idx);
		
		
		
		return result;
		
	}

}
