package com.alj.dream.review.domain;

public class Review {
	
	private String mentee_nm;
	private String comment;
	private String post_nm;
	private String stars;
	
	public Review() {

	}

	public Review(String mentee_nm, String comment, String post_nm, String stars) {
		this.mentee_nm = mentee_nm;
		this.comment = comment;
		this.post_nm = post_nm;
		this.stars = stars;
	}

	public String getMentee_nm() {
		return mentee_nm;
	}

	public void setMentee_nm(String mentee_nm) {
		this.mentee_nm = mentee_nm;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPost_nm() {
		return post_nm;
	}

	public void setPost_nm(String post_nm) {
		this.post_nm = post_nm;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	
	
	
	
	
	
	
	
	

}
