package com.alj.dream.report.domain;

public class ReportInfo {
	
	private String match_idx;
	private String m_email;
	private String m_photo;
	private String report_content;
	private String m_report;
	
	
	public ReportInfo() {
		
	}


	public ReportInfo(String match_idx, String m_email, String m_photo, String report_content, String m_report) {
		this.match_idx = match_idx;
		this.m_email = m_email;
		this.m_photo = m_photo;
		this.report_content = report_content;
		this.m_report = m_report;
	}


	public String getMatch_idx() {
		return match_idx;
	}


	public void setMatch_idx(String match_idx) {
		this.match_idx = match_idx;
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


	public String getReport_content() {
		return report_content;
	}


	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}


	public String getM_report() {
		return m_report;
	}


	public void setM_report(String m_report) {
		this.m_report = m_report;
	}


	
	
	
	
	

	
	
	
	
	
	
	
}
