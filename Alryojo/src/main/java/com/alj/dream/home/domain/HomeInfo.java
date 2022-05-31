package com.alj.dream.home.domain;

public class HomeInfo {
	
	private int memberCount;
	private int postCount;
	private int matchCount;
	
	
	public HomeInfo() {
		// TODO Auto-generated constructor stub
	}


	public HomeInfo(int memberCount, int postCount, int matchCount) {
		this.memberCount = memberCount;
		this.postCount = postCount;
		this.matchCount = matchCount;
	}


	public int getMemberCount() {
		return memberCount;
	}


	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}


	public int getPostCount() {
		return postCount;
	}


	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}


	public int getmatchCount() {
		return matchCount;
	}


	public void setmatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
	
	
	
	
	
	

}
