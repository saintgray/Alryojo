package com.alj.dream.profile.domain;

public class ProfileRequest {

	private int m_idx;
	private String m_nm;
	private String m_email;
	private String m_photo;
	private String line;
	private String loc_nm;
	
	public ProfileRequest() {}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getM_nm() {
		return m_nm;
	}

	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_photo() {
		return m_photo;
	}

	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLoc_nm() {
		return loc_nm;
	}

	public void setLoc_nm(String loc_nm) {
		this.loc_nm = loc_nm;
	}

	@Override
	public String toString() {
		return "ProfileRequest [m_idx=" + m_idx + ", m_nm=" + m_nm + ", m_email=" + m_email + ", m_photo=" + m_photo
				+ ", line=" + line + ", loc_nm=" + loc_nm + "]";
	}
	
}
