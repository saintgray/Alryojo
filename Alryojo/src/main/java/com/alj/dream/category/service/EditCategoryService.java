package com.alj.dream.category.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.category.dao.CategoryDao;


@Service
public class EditCategoryService {
	
	private SqlSessionTemplate sst;
	
	public EditCategoryService() {
		
	}
	@Autowired
	public EditCategoryService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	public int editCategoryName(String name,String idx) {
		
		int result=sst.getMapper(CategoryDao.class).editCategory(name, idx);
		
		return result;
	}
	
	

}
