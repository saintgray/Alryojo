package com.alj.dream.notice.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alj.dream.notice.dao.NoticeDao;
import com.alj.dream.notice.domain.NoticeContent;
import com.alj.dream.notice.domain.NoticeRegisterData;

import security.AccountDetails;

@Service
public class NoticeEditService {

	@Autowired
	private SqlSessionTemplate sst;

	public NoticeContent getNotice(String notice_idx) {
		
		return sst.getMapper(NoticeDao.class).getNotice(notice_idx);
		
	}

	public int updateNotice(Authentication auth, NoticeRegisterData data) {
		// TODO Auto-generated method stub
		
		// 시큐리티의 UserDetails 객체를 상속받은 AccountDetails 객체안에 있는 관리자의 idx를 얻는법
		AccountDetails logininfo = (AccountDetails)auth.getPrincipal();
		String admin_idx= logininfo.getAdmin_idx();
				
		data.setAdmin_idx(admin_idx);
		int result=sst.getMapper(NoticeDao.class).updateNotice(data);
				
		return result;
		
	}
	
	
	
	
	
}
