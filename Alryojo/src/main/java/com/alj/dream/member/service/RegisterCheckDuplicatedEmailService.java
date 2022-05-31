package com.alj.dream.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.member.dao.MemberDao;


@Service
public class RegisterCheckDuplicatedEmailService {
	private SqlSessionTemplate sqt;
	
	public RegisterCheckDuplicatedEmailService() {}
	
	@Autowired
	public RegisterCheckDuplicatedEmailService(SqlSessionTemplate sqt) {
	
		this.sqt = sqt;
	}


	public String selectByEMail(String m_email) {
		
		String result=sqt.getMapper(MemberDao.class).selectByEmail(m_email);
		System.out.println("selectByEMail Result>>>>" + result);
		
		return result;	
	}

}
