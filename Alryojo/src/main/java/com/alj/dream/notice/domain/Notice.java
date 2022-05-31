package com.alj.dream.notice.domain;

public class Notice{
	
	private String notice_idx;
	private String notice_title;
	private String regdate;
	
	public Notice() {
	
	}

	public Notice(String notice_idx, String notice_title, String regdate) {
		this.notice_idx = notice_idx;
		this.notice_title = notice_title;
		this.regdate = regdate;
	}

	public String getNotice_idx() {
		return notice_idx;
	}

	public void setNotice_idx(String notice_idx) {
		this.notice_idx = notice_idx;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	
	
	
	

}
