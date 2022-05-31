package com.alj.dream.qna.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alj.dream.qna.dao.QnaDao;
import com.alj.dream.qna.domain.QnaRegisterData;

import security.AccountDetails;



@Service
public class QnaRegisterService {

	private SqlSessionTemplate sst;
	
	public QnaRegisterService() {
	
	}
	@Autowired
	public QnaRegisterService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	
	public int insertQna(Authentication auth, QnaRegisterData data) {
		int result=0;
		
		
		data.setM_idx(((AccountDetails)auth.getPrincipal()).getM_idx());
		
		result=sst.getMapper(QnaDao.class).insertQna(data);
		
		
		return result;
	}
	
}
