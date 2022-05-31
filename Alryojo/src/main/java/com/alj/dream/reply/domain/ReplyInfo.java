package com.alj.dream.reply.domain;

public class ReplyInfo {
	
	private String reply_idx;
	private String reply_regdate;
	private String admin_nm;
	
	
	public ReplyInfo() {
	
	}


	public ReplyInfo(String reply_idx, String reply_regdate, String admin_nm) {
		this.reply_idx = reply_idx;
		this.reply_regdate = reply_regdate;
		this.admin_nm = admin_nm;
	}


	public String getReply_idx() {
		return reply_idx;
	}


	public void setReply_idx(String reply_idx) {
		this.reply_idx = reply_idx;
	}


	public String getReply_regdate() {
		return reply_regdate;
	}


	public void setReply_regdate(String reply_regdate) {
		this.reply_regdate = reply_regdate;
	}


	public String getAdmin_nm() {
		return admin_nm;
	}


	public void setAdmin_nm(String admin_nm) {
		this.admin_nm = admin_nm;
	}
	
	
	
	
	

}
