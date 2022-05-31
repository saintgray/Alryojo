package com.alj.dream.post.domain;

import java.util.List;
import com.alj.dream.category.domain.Category;

public class SearchParams {

	private int m_idx;
	private String wanted;
	private int cat_idx;
	private int loc_idx;
	private int index;
	private int count;
	private int pageNum;
	
	private List<Category> childrens;


	public SearchParams() {
	}

	public SearchParams(int m_idx, String wanted, int cat_idx, int loc_idx) {
		this.m_idx = m_idx;
		this.wanted = wanted;
		this.cat_idx = cat_idx;
		this.loc_idx=loc_idx;
		
	}



	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getWanted() {
		return wanted;
	}

	public void setWanted(String wanted) {
		this.wanted = wanted;
	}


	public int getCat_idx() {
		return cat_idx;
	}

	public void setCat_idx(int cat_idx) {
		this.cat_idx = cat_idx;
	}
	
	

	public int getLoc_idx() {
		return loc_idx;
	}
	
	
	

	

	public List<Category> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Category> childrens) {
		this.childrens = childrens;
	}


	public void setLoc_idx(int loc_idx) {
		this.loc_idx = loc_idx;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "SearchParams [m_idx=" + m_idx + ", wanted=" + wanted + ", cat_idx=" + cat_idx + ", loc_idx=" + loc_idx
				+ ", index=" + index + ", count=" + count + ", pageNum=" + pageNum + "]";
	}

}
