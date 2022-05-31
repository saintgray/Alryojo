package com.alj.dream.member.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RegisterInfo {
	private int m_idx;
	private String m_email;
    private String m_password;
	private String m_nm;
	private String m_adyn;
	private MultipartFile photo;
	private String m_photo;
	private String loc_idx;
	private List<String> interest;
	
	public RegisterInfo() {
		
		
	}

	public RegisterInfo(int m_idx, String m_email, String m_password, String m_nm, String m_adyn, MultipartFile photo,
			String m_photo, String loc_idx, List<String> interest) {
		this.m_idx = m_idx;
		this.m_email = m_email;
		this.m_password = m_password;
		this.m_nm = m_nm;
		this.m_adyn = m_adyn;
		this.photo = photo;
		this.m_photo = m_photo;
		this.loc_idx = loc_idx;
		this.interest = interest;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_nm() {
		return m_nm;
	}

	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}

	public String getM_adyn() {
		return m_adyn;
	}

	public void setM_adyn(String m_adyn) {
		this.m_adyn = m_adyn;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getM_photo() {
		return m_photo;
	}

	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}

	public String getLoc_idx() {
		return loc_idx;
	}

	public void setLoc_idx(String loc_idx) {
		this.loc_idx = loc_idx;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	
	

	
	
	
	

	
	
	

}
