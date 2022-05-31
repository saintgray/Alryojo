package com.alj.dream.notice.domain;

public class PageRequest {
	
	private String selectPage;
	private String numOfNoticesPerPage;
	private String keyword;
	
	public PageRequest() {
	}

	
	

	public PageRequest(String selectPage, String numOfNoticesPerPage, String keyword) {
		
		this.selectPage = selectPage;
		this.numOfNoticesPerPage = numOfNoticesPerPage;
		this.keyword = keyword;
	}


	

	public String getSelectPage() {
		return selectPage;
	}




	public void setSelectPage(String selectPage) {
		this.selectPage = selectPage;
	}




	public String getNumOfNoticesPerPage() {
		return numOfNoticesPerPage;
	}




	public void setNumOfNoticesPerPage(String numOfNoticesPerPage) {
		this.numOfNoticesPerPage = numOfNoticesPerPage;
	}




	public String getKeyword() {
		return keyword;
	}




	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}




	@Override
	public String toString() {
		return "PageRequest [selectPage=" + selectPage + ", numOfNoticesPerPage=" + numOfNoticesPerPage
				+" keyword=" + keyword;
	}

	

}
