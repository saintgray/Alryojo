package com.alj.dream.profile.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.ProfileRegisterData;

@Service
public class RegisterProfileService {
	
	
	private SqlSessionTemplate sst;
	
	public RegisterProfileService() {

	}
	

	
	@Autowired
	public RegisterProfileService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	
	// 작성한 프로필을 Database 에 저장
	public int insertProfile(ProfileRegisterData data) {
		return sst.getMapper(ProfileDao.class).insertProfile(data);
	}




	
}
