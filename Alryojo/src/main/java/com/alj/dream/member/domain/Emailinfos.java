package com.alj.dream.member.domain;

public class Emailinfos {

	private String m_email_prefix;
	private String m_email_suffix;
	
	public Emailinfos() {
		
	}

	public Emailinfos(String m_email_prefix, String m_email_suffix) {
		
		this.m_email_prefix = m_email_prefix;
		this.m_email_suffix = m_email_suffix;
	}

	public String getM_email_prefix() {
		return m_email_prefix;
	}

	public void setM_email_prefix(String m_email_prefix) {
		this.m_email_prefix = m_email_prefix;
	}

	public String getM_email_suffix() {
		return m_email_suffix;
	}

	public void setM_email_suffix(String m_email_suffix) {
		this.m_email_suffix = m_email_suffix;
	}
	
	
	
}
