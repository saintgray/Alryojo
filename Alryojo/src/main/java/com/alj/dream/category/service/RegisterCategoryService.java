package com.alj.dream.category.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.category.dao.CategoryDao;
import com.alj.dream.category.domain.Category;

@Service
public class RegisterCategoryService {
	
	private SqlSessionTemplate sst;
	
	public RegisterCategoryService() {

	}
	@Autowired
	public RegisterCategoryService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	public Category register(Category data) {
		
		
		sst.getMapper(CategoryDao.class).register(data);
		
		return data;
	}
	

}
