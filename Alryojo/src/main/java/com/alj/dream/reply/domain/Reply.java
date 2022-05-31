package com.alj.dream.reply.domain;

public class Reply {
	
	 
	private String reply_content; 
	private String reply_regdate;
	private String reply_editdate ;
	private String m_idx; 
	private String admin_nm;
	private String qna_title;
	
	
	public Reply() {
		
	}


	public Reply(String reply_content, String reply_regdate, String reply_editdate, String m_idx, String admin_nm,
			String qna_title) {
		this.reply_content = reply_content;
		this.reply_regdate = reply_regdate;
		this.reply_editdate = reply_editdate;
		this.m_idx = m_idx;
		this.admin_nm = admin_nm;
		this.qna_title = qna_title;
	}


	public String getReply_content() {
		return reply_content;
	}


	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}


	public String getReply_regdate() {
		return reply_regdate;
	}


	public void setReply_regdate(String reply_regdate) {
		this.reply_regdate = reply_regdate;
	}


	public String getReply_editdate() {
		return reply_editdate;
	}


	public void setReply_editdate(String reply_editdate) {
		this.reply_editdate = reply_editdate;
	}


	public String getM_idx() {
		return m_idx;
	}


	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}


	public String getAdmin_nm() {
		return admin_nm;
	}


	public void setAdmin_nm(String admin_nm) {
		this.admin_nm = admin_nm;
	}


	public String getQna_title() {
		return qna_title;
	}


	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	
	
	
	
	

}
