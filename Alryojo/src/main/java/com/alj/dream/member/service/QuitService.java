package com.alj.dream.member.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.member.dao.MemberDao;

import security.AccountDetails;

@Service
public class QuitService {
	
	
	private SqlSessionTemplate sst;
	
	public QuitService() {
	
	}
	@Autowired
	public QuitService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	@Transactional
	public int QuitMember(HttpServletRequest req,Authentication auth) {
		
		int result=0;
		
		String m_idx = ((AccountDetails)auth.getPrincipal()).getM_idx();
		
		result=sst.getMapper(MemberDao.class).quitMember(m_idx);
		
		if(result==1) {
			String memberPhotoName=((AccountDetails)auth.getPrincipal()).getPhoto();
			if(!memberPhotoName.equals("defaultprofile.png")) {
				File file= new File(req.getSession().getServletContext().getRealPath("/resources/files/member"),memberPhotoName);
				if(file.exists()) {
					file.delete();
				}
			}
		}
		
		return result;
		
	}
	
	
	
	

}
