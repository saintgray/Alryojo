package com.alj.dream.profile.dao;

import com.alj.dream.profile.domain.MyProfileInfo;
import com.alj.dream.profile.domain.ProfileRegisterData;
import com.alj.dream.profile.domain.ProfileRequest;

public interface ProfileDao {

	MyProfileInfo getProfile(String m_idx);
	int insertProfile(ProfileRegisterData data);
	int deleteProfile(String profile_idx);
	int editProfile(ProfileRegisterData data);
	
	ProfileRequest getWriterProfile(int m_idx);
}
