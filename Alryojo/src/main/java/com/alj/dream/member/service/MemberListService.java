package com.alj.dream.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.member.domain.MemberInfo;
import com.alj.dream.member.domain.MemberListView;
import com.alj.dream.member.domain.PageRequest;

@Service
public class MemberListService {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public MemberListView getMemberList(PageRequest pagereq) {
		
	pagereq.setSelectPage(pagereq.getSelectPage()==0?1:pagereq.getSelectPage());
	pagereq.setNumOfMemberPerPage(pagereq.getNumOfMemberPerPage()==0?5:pagereq.getNumOfMemberPerPage());
	
		
		
	
	int selectPage =pagereq.getSelectPage();
	int numOfMemberPerPage = pagereq.getNumOfMemberPerPage();
	String searchType=pagereq.getSearchType();
	
	int firstMemberIndexOfSelectedPage = numOfMemberPerPage*(selectPage-1);
	pagereq.setFirstMemberIndexOfSelectedPage(firstMemberIndexOfSelectedPage);
	
	
	
	System.out.println(pagereq.getFirstMemberIndexOfSelectedPage());
	System.out.println(pagereq.getNumOfMemberPerPage());
	System.out.println(pagereq.getSelectPage());
	System.out.println(pagereq.getBlacklisted());
	System.out.println(pagereq.getSearchType());
	System.out.println(pagereq.getSortType());
	System.out.println(pagereq.getSortBy());
	
	
	List<MemberInfo> list = sst.getMapper(MemberDao.class).getMemberList(pagereq);
	
	System.out.println("listsize>>");
	System.out.println(list.size());
	
	

	int totalNumOfMember =sst.getMapper(MemberDao.class).getTotalNumOfMember(pagereq);
	int totalPage=totalNumOfMember/numOfMemberPerPage;
	totalPage=totalNumOfMember%numOfMemberPerPage==0?totalPage:totalPage+1;
		
	MemberListView pageView=
			new MemberListView(totalPage,
							   numOfMemberPerPage,
							   selectPage,
							   searchType,
							   pagereq.getKeyword(),
							   pagereq.getBlacklisted(),
							   pagereq.getSortBy(),
							   pagereq.getSortType(),
							  
							   list);
		return pageView;
	}
}
