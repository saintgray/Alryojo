package com.alj.dream.notice.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class NoticeRegisterData {
	
	private String admin_idx;
	private String notice_title;
	private String notice_content;
	private List<MultipartFile> files;
	private String notice_idx;
	
	
	public NoticeRegisterData() {
		
	}

	

	



	public NoticeRegisterData(String admin_idx, String notice_title, String notice_content, List<MultipartFile> files,
			String notice_idx) {
		this.admin_idx = admin_idx;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.files = files;
		this.notice_idx = notice_idx;
	}







	public String getAdmin_idx() {
		return admin_idx;
	}

	public void setAdmin_idx(String admin_idx) {
		this.admin_idx = admin_idx;
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



	public String getNotice_idx() {
		return notice_idx;
	}



	public void setNotice_idx(String notice_idx) {
		this.notice_idx = notice_idx;
	}







	public List<MultipartFile> getFiles() {
		return files;
	}







	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	

	
	
	
	
	

}
