package com.alj.dream.request.domain;

public class RequestMember {

	// 게시물에 문의를 한 회원의 정보
	
	private int match_idx;		// 매칭 고유번호(=채팅방 고유번호)
	private int post_idx;		// 게시물 고유번호
	private int m_idx;			// 회원 고유번호
	private String m_nm;		// 회원 이름
	private String m_photo;		// 회원 사진
	private String match_yn;	// 매칭 여부
	private String line;		// 회원 프로필 한줄소개
	
	public RequestMember() {}
	
	public RequestMember(int match_idx, int post_idx, int m_idx, String m_nm, String m_photo, String match_yn,
			String line) {
		this.match_idx = match_idx;
		this.post_idx = post_idx;
		this.m_idx = m_idx;
		this.m_nm = m_nm;
		this.m_photo = m_photo;
		this.match_yn = match_yn;
		this.line = line;
	}
	
	

	public int getMatch_idx() {
		return match_idx;
	}

	public void setMatch_idx(int match_idx) {
		this.match_idx = match_idx;
	}

	public int getPost_idx() {
		return post_idx;
	}

	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public String getM_nm() {
		return m_nm;
	}

	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}

	public String getM_photo() {
		return m_photo;
	}

	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}

	public String getmatch_yn() {
		return match_yn;
	}

	public void setmatch_yn(String match_yn) {
		this.match_yn = match_yn;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "RequestingMember [match_idx=" + match_idx + ", post_idx=" + post_idx + ", m_idx=" + m_idx + ", m_nm="
				+ m_nm + ", m_photo=" + m_photo + ", match_yn=" + match_yn + ", line=" + line + "]";
	}
	
	
	
}
