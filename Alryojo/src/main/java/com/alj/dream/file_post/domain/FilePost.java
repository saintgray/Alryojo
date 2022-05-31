package com.alj.dream.file_post.domain;

public class FilePost {
	
	private String file_nm;
	private int post_idx;
	private String file_regdate;
	private String file_deldate;
	private String file_exet;
	private int file_size;
	private String file_originnm;
	
	public FilePost() {}

	public FilePost(String file_nm, int post_idx, String file_regdate, String file_deldate, String file_exet,
			int file_size, String file_originnm) {
		this.file_nm = file_nm;
		this.post_idx = post_idx;
		this.file_regdate = file_regdate;
		this.file_deldate = file_deldate;
		this.file_exet = file_exet;
		this.file_size = file_size;
		this.file_originnm = file_originnm;
	}

	public String getFile_nm() {
		return file_nm;
	}

	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}

	public int getPost_idx() {
		return post_idx;
	}

	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}

	public String getFile_regdate() {
		return file_regdate;
	}

	public void setFile_regdate(String file_regdate) {
		this.file_regdate = file_regdate;
	}

	public String getFile_deldate() {
		return file_deldate;
	}

	public void setFile_deldate(String file_deldate) {
		this.file_deldate = file_deldate;
	}

	public String getFile_exet() {
		return file_exet;
	}

	public void setFile_exet(String file_exet) {
		this.file_exet = file_exet;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public String getFile_originnm() {
		return file_originnm;
	}

	public void setFile_originnm(String file_originnm) {
		this.file_originnm = file_originnm;
	}

}
