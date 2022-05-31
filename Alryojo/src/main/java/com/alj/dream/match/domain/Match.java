package com.alj.dream.match.domain;

public class Match {

	// 채팅창이 생길 때 생성되는 match테이블
	
	private int match_idx;			// 매칭 고유번호
	private String mentor_outyn;	// 멘토 방나감여부
	private String mentee_outyn;	// 멘티 방나감여부
	private String match_yn;		// 매칭 여부
	private String match_date;		// 매칭 날짜
	private int  post_idx;			// 게시글 고유번호
	private int  mentee_idx;		// 멘티 고유번호
	private int  mentor_idx;		// 멘토 고유번호
	
	public Match(int match_idx, String mentor_outyn, String mentee_outyn, String match_yn, String match_date,
			int post_idx, int mentee_idx, int mentor_idx) {
		this.match_idx = match_idx;
		this.mentor_outyn = mentor_outyn;
		this.mentee_outyn = mentee_outyn;
		this.match_yn = match_yn;
		this.match_date = match_date;
		this.post_idx = post_idx;
		this.mentee_idx = mentee_idx;
		this.mentor_idx = mentor_idx;
	}

	public Match() {}

	public int getMatch_idx() {
		return match_idx;
	}

	public void setMatch_idx(int match_idx) {
		this.match_idx = match_idx;
	}

	public String getMentor_outyn() {
		return mentor_outyn;
	}

	public void setMentor_outyn(String mentor_outyn) {
		this.mentor_outyn = mentor_outyn;
	}

	public String getMentee_outyn() {
		return mentee_outyn;
	}

	public void setMentee_outyn(String mentee_outyn) {
		this.mentee_outyn = mentee_outyn;
	}

	public String getMatch_yn() {
		return match_yn;
	}

	public void setMatch_yn(String match_yn) {
		this.match_yn = match_yn;
	}

	public String getMatch_date() {
		return match_date;
	}

	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}

	public int getPost_idx() {
		return post_idx;
	}

	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}

	public int getMentee_idx() {
		return mentee_idx;
	}

	public void setMentee_idx(int mentee_idx) {
		this.mentee_idx = mentee_idx;
	}

	public int getMentor_idx() {
		return mentor_idx;
	}

	public void setMentor_idx(int mentor_idx) {
		this.mentor_idx = mentor_idx;
	}

	@Override
	public String toString() {
		return "Match [match_idx=" + match_idx + ", mentor_outyn=" + mentor_outyn + ", mentee_outyn=" + mentee_outyn
				+ ", match_yn=" + match_yn + ", match_date=" + match_date + ", post_idx=" + post_idx + ", mentee_idx="
				+ mentee_idx + ", mentor_idx=" + mentor_idx + "]";
	}
	
	
	
	
	
}
