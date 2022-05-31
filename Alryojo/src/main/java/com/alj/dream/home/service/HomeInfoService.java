package com.alj.dream.home.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.home.dao.HomeDao;
import com.alj.dream.home.domain.HomeInfo;

@Service
public class HomeInfoService {

	
	private SqlSessionTemplate sst;
	
	public HomeInfoService() {
		
	}
	@Autowired
	public HomeInfoService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	
	public HomeInfo getMainInfos() {
		
		return sst.getMapper(HomeDao.class).getCountInfo();
		
	}
	
	
	
}
