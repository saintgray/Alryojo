package com.alj.dream.reply.domain;

public class ReplyRegisterData {
	
	private String reply_content;
	private String m_idx;
	private String admin_idx;
	private String qna_idx;
	
	
	public ReplyRegisterData() {
		
	}


	public ReplyRegisterData(String reply_content, String m_idx, String admin_idx, String qna_idx) {
		this.reply_content = reply_content;
		this.m_idx = m_idx;
		this.admin_idx = admin_idx;
		this.qna_idx = qna_idx;
	}


	public String getReply_content() {
		return reply_content;
	}


	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}


	public String getM_idx() {
		return m_idx;
	}


	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}


	public String getAdmin_idx() {
		return admin_idx;
	}


	public void setAdmin_idx(String admin_idx) {
		this.admin_idx = admin_idx;
	}


	public String getQna_idx() {
		return qna_idx;
	}


	public void setQna_idx(String qna_idx) {
		this.qna_idx = qna_idx;
	}
	
	
	

}
