package com.alj.dream.qna.domain;

public class QnaRegisterData {
	
	
	private String m_idx;
	private String qna_title;
	private String qna_content;
	
	
	public QnaRegisterData() {
		
	}


	public QnaRegisterData(String m_idx, String qna_title, String qna_content) {
		this.m_idx = m_idx;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
	}


	public String getM_idx() {
		return m_idx;
	}


	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}


	public String getQna_title() {
		return qna_title;
	}


	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}


	public String getQna_content() {
		return qna_content;
	}


	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	
	
	
	
	

}
