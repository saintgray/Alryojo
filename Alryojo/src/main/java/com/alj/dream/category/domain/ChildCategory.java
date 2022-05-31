package com.alj.dream.category.domain;

public class ChildCategory {
	
	String cat_idx;
	String cat_nm;
	String childNodeCount;
	String parent_catnm;
	
	public ChildCategory() {
		
	}

	
	
	
	
	
	

	public ChildCategory(String cat_idx, String cat_nm, String childNodeCount, String parent_catnm) {
		this.cat_idx = cat_idx;
		this.cat_nm = cat_nm;
		this.childNodeCount = childNodeCount;
		this.parent_catnm = parent_catnm;
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




	public String getChildNodeCount() {
		return childNodeCount;
	}




	public void setChildNodeCount(String childNodeCount) {
		this.childNodeCount = childNodeCount;
	}

	



	public String getParent_catnm() {
		return parent_catnm;
	}








	public void setParent_catnm(String parent_catnm) {
		this.parent_catnm = parent_catnm;
	}








	@Override
	public String toString() {
		
		return "\n idx >>"+cat_idx+"\n 이름 >>"+cat_nm;
	}
	
	
	

}
