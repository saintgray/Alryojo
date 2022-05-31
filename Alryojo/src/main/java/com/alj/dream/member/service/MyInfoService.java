package com.alj.dream.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.category.domain.ChildCategory;
import com.alj.dream.interest.dao.InterestDao;
import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.member.domain.MyInfo;


@Service
public class MyInfoService {
	
	private SqlSessionTemplate sst;
	
	
	public MyInfoService() {
		
	}

	@Autowired
	public MyInfoService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	@Transactional
	public MyInfo getPrincipalInfos(String principal_idx) {
		
		
		MyInfo info=sst.getMapper(MemberDao.class).selectByIdx(principal_idx);
		
		
		List<ChildCategory> list= sst.getMapper(InterestDao.class).getUserInterest(principal_idx);
		
		info.setList(list);
		
		
		
		return info;
	}
	
	

}
