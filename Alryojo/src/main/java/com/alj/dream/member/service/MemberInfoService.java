package com.alj.dream.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.member.domain.MemberInfo;

@Service
public class MemberInfoService {
	@Autowired
	private SqlSessionTemplate sst;
	
	public MemberInfo getMemberInfo(String m_idx) {
		MemberInfo memberInfo= null;
		
		memberInfo=sst.getMapper(MemberDao.class).getMemberInfo(m_idx);
		
		
		
		
		return memberInfo;
		//return sst.getMapper(MemberDao.class).getMemberInfo(m_idx);
	}
}
