package com.alj.dream.category.domain;

public class Category {
	
	private String cat_idx;
	private String cat_nm;
	private String parent_idx;
	
	
	public Category() {

	}




	public Category(String cat_idx, String cat_nm, String parent_idx) {
		this.cat_idx = cat_idx;
		this.cat_nm = cat_nm;
		this.parent_idx = parent_idx;
	}




	public String getCat_idx() {
		return cat_idx;
	}

	public void setCat_idx(String cat_idx) {
		this.cat_idx = cat_idx;
	}

	public String getCat_nm() {
		return cat_nm;
	}

	public void setCat_nm(String cat_nm) {
		this.cat_nm = cat_nm;
	}




	public String getParent_idx() {
		return parent_idx;
	}




	public void setParent_idx(String parent_idx) {
		this.parent_idx = parent_idx;
	}

	
	
	
	
	
	
	
	
	

}
