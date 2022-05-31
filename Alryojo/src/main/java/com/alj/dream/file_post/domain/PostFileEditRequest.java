package com.alj.dream.file_post.domain;

public class PostFileEditRequest {

	private String file_nm;
	private int post_idx;
	private String file_exet;
	private int file_size;
	private String file_originnm;
	private String update_status;
	
	public PostFileEditRequest() {}
	
	public PostFileEditRequest(String file_nm, int post_idx, String file_exet, int file_size, String file_originnm) {
		this.file_nm = file_nm;
		this.post_idx = post_idx;
		this.file_exet = file_exet;
		this.file_size = file_size;
		this.file_originnm = file_originnm;
	}
	
	public PostFileEditRequest(String file_nm, int post_idx, String file_exet, int file_size, String file_originnm, String update_status) {
		this.file_nm = file_nm;
		this.post_idx = post_idx;
		this.file_exet = file_exet;
		this.file_size = file_size;
		this.file_originnm = file_originnm;
		this.update_status = update_status;
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

	public String getUpdate_status() {
		return update_status;
	}

	public void setUpdate_status(String update_status) {
		this.update_status = update_status;
	}

	@Override
	public String toString() {
		return "PostFileRequest [file_nm=" + file_nm + ", post_idx=" + post_idx + ", file_exet=" + file_exet
				+ ", file_size=" + file_size + ", file_originnm=" + file_originnm + ", update_status=" + update_status + "]";
	}
	
	public PostFileRequest getFileRequest() {
		return new PostFileRequest(file_nm, post_idx, file_exet, file_size, file_originnm);
	}
	
}
