package com.alj.dream.category.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.category.dao.CategoryDao;
import com.alj.dream.category.domain.Category;
import com.alj.dream.category.domain.CategoryInfo;
import com.alj.dream.category.domain.CategorySiblingsInfo;
import com.alj.dream.category.domain.ChildCategory;

@Service
public class CategoryListService {
	
	private SqlSessionTemplate sst;
	
	public CategoryListService() {
		
	}
	
	@Autowired
	public CategoryListService(SqlSessionTemplate sst) {
	
		this.sst = sst;
	}


	public List<ChildCategory> getList(List<String> interest) {
		
		
		List<ChildCategory> list=null;
		
		
		list=sst.getMapper(CategoryDao.class).getChildCategory(interest);
		
		
		// System.out.println(list);
		
		
		return list;
			
		
	}
	
	
	
	
	public CategorySiblingsInfo getSiblings(String idx){
		List<ChildCategory> list =sst.getMapper(CategoryDao.class).getSiblings(idx);
		Category parent=sst.getMapper(CategoryDao.class).getParentInfo(idx);
		
		
		CategorySiblingsInfo info=new CategorySiblingsInfo(parent, list);
		
		return info;
	}
	
	
	
	public List<CategoryInfo> selectAll(){
		return sst.getMapper(CategoryDao.class).selectAll();
	}
	
	
	
	public String getCategoryIdxByName(String name) {
		return sst.getMapper(CategoryDao.class).getCatIdxByName(name);
	}
}
