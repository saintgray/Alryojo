package com.alj.dream.post.domain;

public class PostListInfo {
	
	private int post_idx;
	private String post_nm;
	private int m_idx;
	private int cat_idx;
	private String cat_nm;
	private int loc_idx;
	private String loc_nm;
	private int match_count;
	
	public PostListInfo() {}

	public PostListInfo(int post_idx, String post_nm, int m_idx, int cat_idx, String cat_nm, int loc_idx, String loc_nm, int match_count) {
		this.post_idx = post_idx;
		this.post_nm = post_nm;
		this.m_idx = m_idx;
		this.cat_idx = cat_idx;
		this.cat_nm = cat_nm;
		this.loc_idx = loc_idx;
		this.loc_nm = loc_nm;
		this.match_count = match_count;
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

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
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

	public int getLoc_idx() {
		return loc_idx;
	}

	public void setLoc_idx(int loc_idx) {
		this.loc_idx = loc_idx;
	}

	public String getLoc_nm() {
		return loc_nm;
	}

	public void setLoc_nm(String loc_nm) {
		this.loc_nm = loc_nm;
	}

	public int getMatch_count() {
		return match_count;
	}

	public void setMatch_count(int match_count) {
		this.match_count = match_count;
	}

}
