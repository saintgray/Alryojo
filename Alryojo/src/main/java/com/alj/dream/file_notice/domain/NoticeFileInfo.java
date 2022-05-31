package com.alj.dream.file_notice.domain;

public class NoticeFileInfo {
	
	private String file_nm;
	private String file_size;
	private String file_exet;
	private String file_originnm;
	private String notice_idx;
	
	
	public NoticeFileInfo() {
		
	}


	public NoticeFileInfo(String file_nm, String file_size, String file_exet, String file_originnm, String notice_idx) {
		this.file_nm = file_nm;
		this.file_size = file_size;
		this.file_exet = file_exet;
		this.file_originnm = file_originnm;
		this.notice_idx = notice_idx;
	}


	public String getFile_nm() {
		return file_nm;
	}


	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}


	public String getFile_size() {
		return file_size;
	}


	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}


	public String getFile_exet() {
		return file_exet;
	}


	public void setFile_exet(String file_exet) {
		this.file_exet = file_exet;
	}


	public String getFile_originnm() {
		return file_originnm;
	}


	public void setFile_originnm(String file_originnm) {
		this.file_originnm = file_originnm;
	}


	public String getNotice_idx() {
		return notice_idx;
	}


	public void setNotice_idx(String notice_idx) {
		this.notice_idx = notice_idx;
	}

	



	
}
