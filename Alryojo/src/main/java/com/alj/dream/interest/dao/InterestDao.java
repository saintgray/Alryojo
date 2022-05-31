package com.alj.dream.interest.dao;

import java.util.List;

import com.alj.dream.category.domain.ChildCategory;


public interface InterestDao {
	
	int registerUserInterest(List<String> interest, String principal_idx);
	List<ChildCategory> getUserInterest(String principal_idx);
	int deleteAllInterest(String m_idx);

}
