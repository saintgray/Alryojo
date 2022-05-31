package com.alj.dream.profile.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.profile.dao.ProfileDao;

@Service
public class DeleteProfileService {
	
	
	private SqlSessionTemplate sst;
	
	public DeleteProfileService() {

	}
	@Autowired
	public DeleteProfileService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	public int deleteProfile(String profile_idx) {
		return sst.getMapper(ProfileDao.class).deleteProfile(profile_idx);	
	}
	
	
	
	
}
