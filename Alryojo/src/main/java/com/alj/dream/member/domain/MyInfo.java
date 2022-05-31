package com.alj.dream.member.domain;

import java.util.List;

import com.alj.dream.category.domain.ChildCategory;


public class MyInfo {

	private String m_email;
	private String m_nm;
	private String m_photo;
	private String m_regdate;
	private List<ChildCategory> list;
	
	


	public MyInfo() {
	
	}




	public MyInfo(String m_email, String m_nm, String m_photo, String m_regdate, List<ChildCategory> list) {
		this.m_email = m_email;
		this.m_nm = m_nm;
		this.m_photo = m_photo;
		this.m_regdate = m_regdate;
		this.list = list;
	}




	public String getM_email() {
		return m_email;
	}




	public void setM_email(String m_email) {
		this.m_email = m_email;
	}




	public String getM_nm() {
		return m_nm;
	}




	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}




	public String getM_photo() {
		return m_photo;
	}




	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}




	public String getM_regdate() {
		return m_regdate;
	}




	public void setM_regdate(String m_regdate) {
		this.m_regdate = m_regdate;
	}




	public List<ChildCategory> getList() {
		return list;
	}




	public void setList(List<ChildCategory> list) {
		this.list = list;
	}




	
	
	 

	
	
}
