package com.alj.dream.admin.dao;

import com.alj.dream.admin.domain.Admin;
import com.alj.dream.member.domain.EditInfos;
import com.alj.dream.member.domain.MyInfo;

public interface AdminDao {
	
	MyInfo selectByIdx(String principal_idx);
	int editAdmin(EditInfos info);
	int insertAdmin(Admin admin);

}
