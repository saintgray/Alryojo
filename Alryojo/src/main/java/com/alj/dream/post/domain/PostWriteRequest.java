package com.alj.dream.post.domain;

import java.util.List;

import com.alj.dream.file_post.domain.PostFileRequest;

public class PostWriteRequest {
	
	private int post_idx;
	private String post_nm;
	private String post_content;
	private int cat_idx;
	private String cat_nm;
	private String wanted;
	private String m_idx;
	private List<PostFileRequest> fileList;
	
	public PostWriteRequest() {}

	public PostWriteRequest(int post_idx, String post_nm, String post_content, int cat_idx, String cat_nm, String wanted, String m_idx) {
		this.post_idx = post_idx;
		this.post_nm = post_nm;
		this.post_content = post_content;
		this.cat_idx = cat_idx;
		this.cat_nm = cat_nm;
		this.wanted = wanted;
		this.m_idx = m_idx;
	}
	
	public PostWriteRequest(int post_idx, String post_nm, String post_content, int cat_idx, String wanted, String m_idx,
			List<PostFileRequest> fileList) {
		this.post_idx = post_idx;
		this.post_nm = post_nm;
		this.post_content = post_content;
		this.cat_idx = cat_idx;
		this.wanted = wanted;
		this.m_idx = m_idx;
		this.fileList = fileList;
	}

	public int getPost_idx() {
		return post_idx;
	}

	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}

	public String getPost_nm() {
		return post_nm;
	}

	public void setPost_nm(String post_nm) {
		this.post_nm = post_nm;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public int getCat_idx() {
		return cat_idx;
	}

	public void setCat_idx(int cat_idx) {
		this.cat_idx = cat_idx;
	}

	public String getCat_nm() {
		return cat_nm;
	}

	public void setCat_nm(String cat_nm) {
		this.cat_nm = cat_nm;
	}

	public String getWanted() {
		return wanted;
	}

	public void setWanted(String wanted) {
		this.wanted = wanted;
	}

	public String getM_idx() {
		return m_idx;
	}

	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}

	public List<PostFileRequest> getFileList() {
		return fileList;
	}

	public void setFileList(List<PostFileRequest> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "PostWriteRequest [post_idx=" + post_idx + ", post_nm=" + post_nm + ", post_content=" + post_content
				+ ", cat_idx=" + cat_idx + ", cat_nm=" + cat_nm + ", wanted=" + wanted + ", m_idx=" + m_idx
				+ ", fileList=" + fileList + "]";
	}

}
