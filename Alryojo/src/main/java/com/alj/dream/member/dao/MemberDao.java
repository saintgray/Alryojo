package com.alj.dream.member.dao;

import java.util.List;

import com.alj.dream.member.domain.EditInfos;
import com.alj.dream.member.domain.MemberInfo;

import com.alj.dream.member.domain.PageRequest;

import com.alj.dream.member.domain.MyInfo;

import com.alj.dream.member.domain.RegisterInfo;

public interface MemberDao {
	
	int insertMember(RegisterInfo info);
	int editMember(EditInfos info);
	String selectByEmail(String m_email);
	List<MemberInfo> getMemberList(PageRequest req);
	int getTotalNumOfMember(PageRequest pagereq);
	MemberInfo getMemberInfo(String m_idx);
	MyInfo selectByIdx(String principal_idx);
	int quitMember(String m_idx);
	int updateMemberPwByKeyCode(String rawKeyCode,String newPw);
	int updateTempCodeByEmail(String tempCode, String email);
	int getCountByKeyCode(String keyCode);
	int expireTempCode();
	
	
	
}
