package com.alj.dream.request.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alj.dream.match.dao.MatchDao;
import com.alj.dream.request.dao.RequestDao;
import com.alj.dream.request.domain.RequestGroup;
import com.alj.dream.request.domain.RequestMember;

import security.AccountDetails;
@Service
public class RequestGroupService {
	
	private RequestDao dao;
	private MatchDao mdao;
	@Autowired 
	private SqlSessionTemplate template;
	
	
	
	// 받은 제의를 가져 올 메소드
	public List<RequestGroup> getRequestGroup (Authentication auth){
		
		dao = template.getMapper(RequestDao.class);
	
		AccountDetails logininfo = (AccountDetails)auth.getPrincipal();
		int myidx = Integer.parseInt(logininfo.getM_idx());
		String mytype = logininfo.getM_type();
		
		String wanted = null;						// 게시물의 제안 대상
		
		if (mytype.equalsIgnoreCase("mentee")) {
			wanted="mentor";
			
		}else if(mytype.equalsIgnoreCase("mentor")) {
			wanted="mentee";
		}
		
		
		
		String overfive = null;
		
		
		
		List<RequestMember> rmemberList = new ArrayList<RequestMember>();
		List<RequestGroup> rgroupList = dao.selectRequestPostByMIdx(myidx, wanted);	
		
		
		for(RequestGroup post : rgroupList) {
			
			
			rmemberList = dao.selectRequestMemberByPostIdx(post.getPost_idx(), wanted);	
			
			post.setList(rmemberList);
			//rgroupList.get(i).setList(rmemberList);
			
		
			
			// 2022 02 21 종현 수정 (totalMember 구하기)
			
			// totalMember = dao.totalRequestMemberByPostIdx(postidx);
			//System.out.println("totalMember:"+totalMember);
			
			// 가져온 멤버들(List<ReguestMember> 의 size 가 곧 totalMember 이다. 즉, DB에 같은 조건으로 갯수만 구하러 다녀 올 필요가 없다.
			// requestMapper.xml 의 id=totalRequestMemberByPostIdx 와 Dao 의 totalRequestMemerByPostIdx 모두 삭제해도 됨 (필요없음)
			int totalMember =rmemberList.size();
			
			// 화면에 표시할  멤버수는 최대 4명이다. 그 이상은 ...으로 표기할 예정
			if(totalMember > 4) {
				overfive = "Y";
			}else {
				overfive = "N";
			}
			
			
			post.setOverfive(overfive);
			
			
			// i++;
		}

		return rgroupList;
	}
	
	
	// 매칭 여부 업데이트
	public int updateMatchYN(String matchYN, int matchidx) {
		
		mdao = template.getMapper(MatchDao.class);
		
		int resultCnt = mdao.updateMatchYN(matchYN, matchidx);
		System.out.println("매칭업뎃확인");
		return resultCnt;
	}
	
}
