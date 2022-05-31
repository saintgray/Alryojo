package com.alj.dream.reply.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alj.dream.reply.dao.ReplyDao;
import com.alj.dream.reply.domain.ReplyRegisterData;

import security.AccountDetails;

@Service
public class ReplyRegisterService {
	
	private SqlSessionTemplate sst;
	
	public ReplyRegisterService() {
		
	}
	@Autowired
	public ReplyRegisterService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	public int registerReply(ReplyRegisterData data, Authentication auth) {
		AccountDetails logininfo = (AccountDetails)auth.getPrincipal();
		data.setAdmin_idx(logininfo.getAdmin_idx());
		
		return sst.getMapper(ReplyDao.class).insertReply(data);
		
	}
	
	
	
	
	

}
