package com.alj.dream.post.domain;

public class Post {

	private int post_idx;			// 게시글고유번호
	private String post_nm;			// 제목
	private String post_content;	// 내용
	private String post_regdate;	// 등록일
	private String post_editdate;	// 수정일
	private String post_deldate;	// 삭제일
	private int cat_idx;			// 카테고리고유번호
	private int post_rate;			// 조회수
	private String wanted;			// WANTED : 멘토/멘티 중 구하는 대상
	private int m_idx;				// 회원고유번호 : 글쓴이의 고유번호
	
	public Post() {}

	public Post(int post_idx, String post_nm, String post_content, String post_regdate, String post_editdate,
			String post_deldate, int cat_idx, int post_rate, String wanted, int m_idx) {
		this.post_idx = post_idx;
		this.post_nm = post_nm;
		this.post_content = post_content;
		this.post_regdate = post_regdate;
		this.post_editdate = post_editdate;
		this.post_deldate = post_deldate;
		this.cat_idx = cat_idx;
		this.post_rate = post_rate;
		this.wanted = wanted;
		this.m_idx = m_idx;
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

	public String getPost_regdate() {
		return post_regdate;
	}

	public void setPost_regdate(String post_regdate) {
		this.post_regdate = post_regdate;
	}

	public String getPost_editdate() {
		return post_editdate;
	}

	public void setPost_editdate(String post_editdate) {
		this.post_editdate = post_editdate;
	}

	public String getPost_deldate() {
		return post_deldate;
	}

	public void setPost_deldate(String post_deldate) {
		this.post_deldate = post_deldate;
	}

	public int getCat_idx() {
		return cat_idx;
	}

	public void setCat_idx(int cat_idx) {
		this.cat_idx = cat_idx;
	}

	public int getPost_rate() {
		return post_rate;
	}

	public void setPost_rate(int post_rate) {
		this.post_rate = post_rate;
	}

	public String getWanted() {
		return wanted;
	}

	public void setWanted(String wanted) {
		this.wanted = wanted;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	
}