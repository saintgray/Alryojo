package com.alj.dream.request.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.MyProfileInfo;
import com.alj.dream.request.dao.RequestDao;
import com.alj.dream.request.domain.RequestGroup;
import com.alj.dream.request.domain.RequestMember;

import security.AccountDetails;

@Service
public class RequestPostService {

	RequestDao dao;
	ProfileDao pdao;
	@Autowired
	SqlSessionTemplate template;
	
	
	public RequestGroup getRequestPost (int postIdx,Authentication auth){
		
		AccountDetails logininfo = (AccountDetails)auth.getPrincipal();
		// int myidx = Integer.parseInt(logininfo.getM_idx());
		String mytype = logininfo.getM_type();
		
		String wanted = null;
		
		if(mytype.equalsIgnoreCase("mentee")) {
			wanted="mentor";
		}else if(mytype.equalsIgnoreCase("mentor")) {
			wanted="mentee";
		}

		System.out.println("RequestPostService : getRequestPost 진입");
		
		dao = template.getMapper(RequestDao.class);

		
		// 정보에 맞는 게시글 가져오기
		RequestGroup rgroup = dao.selectRequestPostByPostIdx(postIdx,wanted);
		System.out.println("rgroup : "+rgroup);
		//postidx에 맞는멤버리스트 가져오기
		// 종현 수정
		List<RequestMember> memList = dao.selectRequestMemberByPostIdx(postIdx, rgroup.getWanted());
		System.out.println("memList 정보 :"+ memList);
		
		//RequestGroup 객체에 멤버 객체 저장하기
		rgroup.setList(memList);
		
		return rgroup;
	}
	
	
	public MyProfileInfo chkProfile(int myidx) {
		System.out.println("RequestPostService : chkProfile진입");
		
		String m_idx = Integer.toString(myidx);
		
		pdao = template.getMapper(ProfileDao.class);
		
		MyProfileInfo info = pdao.getProfile(m_idx);  
		
		System.out.println("info:"+info);
		return info;
		
	}
	
}
