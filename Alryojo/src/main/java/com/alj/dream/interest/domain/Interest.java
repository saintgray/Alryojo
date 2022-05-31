package com.alj.dream.interest.domain;

public class Interest {
	
	private String m_idx;
	private String cat_idx;
	
	
	public Interest() {
		
	}


	public Interest(String m_idx, String cat_idx) {
		
		this.m_idx = m_idx;
		this.cat_idx = cat_idx;
	}


	public String getM_idx() {
		return m_idx;
	}


	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}


	public String getCat_idx() {
		return cat_idx;
	}


	public void setCat_idx(String cat_idx) {
		this.cat_idx = cat_idx;
	}
	
	
	
	

}
