package com.alj.dream.reply.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alj.dream.reply.dao.ReplyDao;
import com.alj.dream.reply.domain.Reply;
import com.alj.dream.reply.exception.NotThisUserReply;

import security.AccountDetails;

@Service
public class ReplyContentsService {

	
	
	
	private SqlSessionTemplate sst;
	
	
	public ReplyContentsService() {
		
	}

	@Autowired
	public ReplyContentsService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	public Reply getContent(String reply_idx, Authentication auth) throws NotThisUserReply {
		
		// 1. 로그인한 유저의 고유번호를 Authentication 객체안에서 구한다.
		String loginUserIdx=((AccountDetails)auth.getPrincipal()).getM_idx();
		
		Reply result =sst.getMapper(ReplyDao.class).getContent(reply_idx);
		
		// 2. 만약 답변대상자의 고유번호가 현재 로그인한 사람의 고유번호와 다르다면
		// 고의로 예외를 발생시켜 Controller 로 보냄
		if(!result.getM_idx().equals(loginUserIdx)) {
			throw new NotThisUserReply();
		}
		
		
		
		return result;
		
		
		
	}
	
	
	
}
