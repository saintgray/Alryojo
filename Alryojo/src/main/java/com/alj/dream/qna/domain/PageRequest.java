package com.alj.dream.qna.domain;

public class PageRequest {
	
	private int selectPage;		//클릭한 현재페이지
	private int numOfQnaPerPage;	//한 페이지당 보여줄 qna 갯수
	private int numOfPagePerPage;
	private int curPageIndex;
	private String m_idx;
	private int selectPageFirstIndex;
	
	
	public PageRequest() {
		this.selectPage=1;
		this.curPageIndex=-1;
		this.numOfPagePerPage=10;
		this.numOfQnaPerPage=5;
		
	}
	
	

	


	public PageRequest(int selectPage, int numOfQnaPerPage, int numOfPagePerPage, int curPageIndex, String m_idx) {
		this.selectPage = selectPage;
		this.numOfQnaPerPage = numOfQnaPerPage;
		this.numOfPagePerPage = numOfPagePerPage;
		this.curPageIndex = curPageIndex;
		this.m_idx = m_idx;
	}






	public int getSelectPageFirstIndex() {
		return selectPageFirstIndex;
	}






	public void setSelectPageFirstIndex(int selectPageFirstIndex) {
		this.selectPageFirstIndex = selectPageFirstIndex;
	}






	public int getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
	}

	public int getNumOfQnaPerPage() {
		return numOfQnaPerPage;
	}

	public void setNumOfQnaPerPage(int numOfQnaPerPage) {
		this.numOfQnaPerPage = numOfQnaPerPage;
	}

	public int getNumOfPagePerPage() {
		return numOfPagePerPage;
	}

	public void setNumOfPagePerPage(int numOfPagePerPage) {
		this.numOfPagePerPage = numOfPagePerPage;
	}

	public int getCurPageIndex() {
		return curPageIndex;
	}

	public void setCurPageIndex(int curPageIndex) {
		this.curPageIndex = curPageIndex;
	}






	public String getM_idx() {
		return m_idx;
	}






	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}
	
	


	
	

	
	
}
