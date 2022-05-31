package com.alj.dream.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.member.dao.MemberDao;

@Service
public class FindAccountService {
	
	private SqlSessionTemplate sst;
	
	public FindAccountService() {

	}
	@Autowired
	public FindAccountService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	public int updateTempCode(String tempCode, String email) {
		return sst.getMapper(MemberDao.class).updateTempCodeByEmail(tempCode, email);
	}
	

}
