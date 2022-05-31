package com.alj.dream.notice.domain;

import java.util.List;

import com.alj.dream.file_notice.domain.NoticeFileInfo;

public class NoticeContent {
	
	private String notice_idx;
	private String notice_title;
	private String notice_content;
	private String notice_regdate;
	private String notice_editdate;
	private String admin_nm;
	private String admin_idx;
	List<NoticeFileInfo> files;
	
	
	public NoticeContent() {
		
	}


	

	public NoticeContent(String notice_idx, String notice_title, String notice_content, String notice_regdate,
			String notice_editdate, String admin_nm, String admin_idx, List<NoticeFileInfo> files) {
		this.notice_idx = notice_idx;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_regdate = notice_regdate;
		this.notice_editdate = notice_editdate;
		this.admin_nm = admin_nm;
		this.admin_idx = admin_idx;
		this.files = files;
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


	public String getNotice_content() {
		return notice_content;
	}


	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}


	public String getNotice_regdate() {
		return notice_regdate;
	}


	public void setNotice_regdate(String notice_regdate) {
		this.notice_regdate = notice_regdate;
	}


	public String getNotice_editdate() {
		return notice_editdate;
	}


	public void setNotice_editdate(String notice_editdate) {
		this.notice_editdate = notice_editdate;
	}


	public String getAdmin_nm() {
		return admin_nm;
	}


	public void setAdmin_nm(String admin_nm) {
		this.admin_nm = admin_nm;
	}


	public String getAdmin_idx() {
		return admin_idx;
	}


	public void setAdmin_idx(String admin_idx) {
		this.admin_idx = admin_idx;
	}




	public List<NoticeFileInfo> getFiles() {
		return files;
	}




	public void setFiles(List<NoticeFileInfo> files) {
		this.files = files;
	}


	
	
	

}
