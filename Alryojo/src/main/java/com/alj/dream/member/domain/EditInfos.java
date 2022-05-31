package com.alj.dream.member.domain;

import org.springframework.web.multipart.MultipartFile;

public class EditInfos {

	private String m_idx;
	private String m_nm;
	private MultipartFile photo;
	private String m_password;
	private String m_photo;
	
	public EditInfos() {

	}
	
	
	public EditInfos(String m_idx, String m_nm, MultipartFile photo, String m_password, String m_photo) {
		this.m_idx = m_idx;
		this.m_nm = m_nm;
		this.photo = photo;
		this.m_password = m_password;
		this.m_photo = m_photo;
	}


	public String getM_idx() {
		return m_idx;
	}
	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}
	public String getM_nm() {
		return m_nm;
	}
	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}


	public String getM_photo() {
		return m_photo;
	}


	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}
	
	

	
	







































	
	
	
	
	
	
	
	
}
