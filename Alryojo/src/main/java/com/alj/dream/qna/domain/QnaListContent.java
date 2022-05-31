package com.alj.dream.qna.domain;

public class QnaListContent {

	private String m_idx;
	private String m_nm;
	private String m_email;
	private String qna_idx;
	private String qna_title;
    private String qna_content;
	private String qna_regdate;
	
	public QnaListContent() {
		
	}

	public QnaListContent(String m_idx, String m_nm, String m_email, String qna_idx, String qna_title,
			String qna_content, String qna_regdate) {
		this.m_idx = m_idx;
		this.m_nm = m_nm;
		this.m_email = m_email;
		this.qna_idx = qna_idx;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_regdate = qna_regdate;
	}

	public String getM_idx() {
		return m_idx;
	}

	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}

	public String getM_nm() {
		return m_nm;
	}

	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getQna_idx() {
		return qna_idx;
	}

	public void setQna_idx(String qna_idx) {
		this.qna_idx = qna_idx;
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

	public String getQna_regdate() {
		return qna_regdate;
	}

	public void setQna_regdate(String qna_regdate) {
		this.qna_regdate = qna_regdate;
	}

	
	
	
	
	
}
