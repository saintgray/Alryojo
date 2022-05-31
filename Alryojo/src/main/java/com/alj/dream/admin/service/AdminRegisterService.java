package com.alj.dream.admin.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alj.dream.admin.dao.AdminDao;
import com.alj.dream.admin.domain.Admin;
import com.alj.dream.member.domain.Emailinfos;
import com.alj.dream.util.mail.EMailUtil;
@Service
public class AdminRegisterService {
	
	
	private SqlSessionTemplate sst;
	private BCryptPasswordEncoder encoder;
	
	public AdminRegisterService() {

	}
	@Autowired
	public AdminRegisterService(SqlSessionTemplate sst, BCryptPasswordEncoder encoder) {
		this.sst = sst;
		this.encoder = encoder;
	}
	
	
	
	public int insertAdmin(Emailinfos info, Admin admin) {
		
		int result=0; 
		
		admin.setAdmin_email(EMailUtil.getUserEmail(info));
		admin.setAdmin_nm(info.getM_email_prefix());
		admin.setAdmin_password(encoder.encode(admin.getAdmin_password()));
		result=sst.getMapper(AdminDao.class).insertAdmin(admin);
		
		return result;
		
	}
	
	

}
