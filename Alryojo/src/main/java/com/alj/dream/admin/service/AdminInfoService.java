package com.alj.dream.admin.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.admin.dao.AdminDao;
import com.alj.dream.member.domain.MyInfo;

@Service
public class AdminInfoService {

	
	
	private SqlSessionTemplate sst;
	
	public AdminInfoService() {

	}
	@Autowired
	public AdminInfoService(SqlSessionTemplate sst) {
		this.sst = sst;
	}

	
	
	public MyInfo getAdminInfo(String principal_idx) {
		
		
		return sst.getMapper(AdminDao.class).selectByIdx(principal_idx);
		
	}
	
}
