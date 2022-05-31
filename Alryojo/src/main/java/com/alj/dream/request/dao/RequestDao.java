package com.alj.dream.request.dao;

import java.util.List;

import com.alj.dream.request.domain.RequestGroup;
import com.alj.dream.request.domain.RequestMember;

public interface RequestDao {

	/* int selectTotalPost(int idx, String wanted); */						

	List<RequestGroup> selectRequestPostByMIdx(int idx, String wanted);

	List<RequestMember> selectRequestMemberByPostIdx(int postidx, String wanted); 																						

	RequestGroup selectRequestPostByPostIdx(int myidx, String wanted);
	
	int totalRequestMemberByPostIdx(int postidx);
}