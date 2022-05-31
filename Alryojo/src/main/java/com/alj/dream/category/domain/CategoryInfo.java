package com.alj.dream.category.domain;

public class CategoryInfo {
	
	private int child_idx;
	private String cat_nm;
	private int parent_idx;
	private int childNodeCount;
	
	
	public CategoryInfo() {
		
	}


	public CategoryInfo(int child_idx, String cat_nm, int parent_idx, int childNodeCount) {
		this.child_idx = child_idx;
		this.cat_nm = cat_nm;
		this.parent_idx = parent_idx;
		this.childNodeCount = childNodeCount;
	}


	public int getChild_idx() {
		return child_idx;
	}


	public void setChild_idx(int child_idx) {
		this.child_idx = child_idx;
	}


	public String getCat_nm() {
		return cat_nm;
	}


	public void setCat_nm(String cat_nm) {
		this.cat_nm = cat_nm;
	}


	public int getParent_idx() {
		return parent_idx;
	}


	public void setParent_idx(int parent_idx) {
		this.parent_idx = parent_idx;
	}


	public int getChildNodeCount() {
		return childNodeCount;
	}


	public void setChildNodeCount(int childNodeCount) {
		this.childNodeCount = childNodeCount;
	}
	
	
	
	
	

}
