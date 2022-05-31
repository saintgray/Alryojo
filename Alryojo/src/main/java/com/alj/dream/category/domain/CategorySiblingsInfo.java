package com.alj.dream.category.domain;

import java.util.List;

public class CategorySiblingsInfo {
	
	private Category parentInfo;
	private List<ChildCategory> list;
	
	public CategorySiblingsInfo() {
		
	}

	public CategorySiblingsInfo(Category parentInfo, List<ChildCategory> list) {
		this.parentInfo = parentInfo;
		this.list = list;
	}

	public Category getParentInfo() {
		return parentInfo;
	}

	public void setParentInfo(Category parentInfo) {
		this.parentInfo = parentInfo;
	}

	public List<ChildCategory> getList() {
		return list;
	}

	public void setList(List<ChildCategory> list) {
		this.list = list;
	}


	
	
	
	
	

}
