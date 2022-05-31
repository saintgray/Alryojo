package com.alj.dream.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.member.exception.KeyCodeExpiredException;

@Service
public class ResettingNewPwService {
	
	private SqlSessionTemplate sst;
	private BCryptPasswordEncoder encoder;
	
	
	public ResettingNewPwService() {
		
	}
	
	
	@Autowired
	public ResettingNewPwService(SqlSessionTemplate sst, BCryptPasswordEncoder encoder) {
		this.sst = sst;
		this.encoder = encoder;
	}



	public int resettingPw(String keyCode,String newPw) throws KeyCodeExpiredException {
		
		int tempCodeCount=sst.getMapper(MemberDao.class).getCountByKeyCode(keyCode);
		
		if(tempCodeCount==0) {
			throw new KeyCodeExpiredException();
		}
		
		return sst.getMapper(MemberDao.class).updateMemberPwByKeyCode(keyCode, encoder.encode(newPw));
		
	}
	
	

}
