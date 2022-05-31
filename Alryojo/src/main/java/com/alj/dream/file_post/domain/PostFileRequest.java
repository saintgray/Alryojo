package com.alj.dream.file_post.domain;

public class PostFileRequest {

	private String file_nm;
	private int post_idx;
	private String file_exet;
	private int file_size;
	private String file_originnm;
	
	public PostFileRequest() {}
	
	public PostFileRequest(String file_nm, int post_idx, String file_exet, int file_size, String file_originnm) {
		this.file_nm = file_nm;
		this.post_idx = post_idx;
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

	@Override
	public String toString() {
		return "PostFileRequest [file_nm=" + file_nm + ", post_idx=" + post_idx + ", file_exet=" + file_exet
				+ ", file_size=" + file_size + ", file_originnm=" + file_originnm + "]";
	}
	
	public String getFileName() {
		return file_nm + "." + file_exet;
	}
	
}
