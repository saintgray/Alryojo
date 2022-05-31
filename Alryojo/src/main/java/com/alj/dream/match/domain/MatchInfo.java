package com.alj.dream.match.domain;

public class MatchInfo {

	private int match_idx;
	private int post_idx;
	private String match_yn;
	private int mentor_idx;
	private int mentee_idx;
	
	public MatchInfo() {}

	public int getMatch_idx() {
		return match_idx;
	}

	public int getPost_idx() {
		return post_idx;
	}
	
	public String getMatch_yn() {
		return match_yn;
	}

	public int getMentor_idx() {
		return mentor_idx;
	}

	public int getMentee_idx() {
		return mentee_idx;
	}

	@Override
	public String toString() {
		return "MatchInfo [match_idx=" + match_idx + ", post_idx=" + post_idx + ", match_yn=" + match_yn
				+ ", mentor_idx=" + mentor_idx + ", mentee_idx=" + mentee_idx + "]";
	}
	
}
