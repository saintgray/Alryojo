package com.alj.dream.profile.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.ProfileRegisterData;

@Service
public class ProfileEditService {
	
	private SqlSessionTemplate sst;
	
	public ProfileEditService() {
	
	}
	@Autowired
	public ProfileEditService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	public int updateProfile(ProfileRegisterData data) {
		return sst.getMapper(ProfileDao.class).editProfile(data);	
	}
	
	
	

}
