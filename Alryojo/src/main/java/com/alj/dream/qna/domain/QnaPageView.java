package com.alj.dream.qna.domain;

import java.util.List;

public class QnaPageView {

	private int totalPage;			//총 페이지 수
	private int numOfQnaPerPage;	//한 페이지당 보여줄 qna 갯수
	private int selectPage;			//선택한 현재페이지
	private List<Qna> qnaList;
	// 한 화면에 출력할 페이지 번호 갯수(10개 : 1~10페이지, 21~30페이지...)
	private int numOfPagePerPage;
	// 현재 페이징 인덱스 ( 0 : 1~10페이지, 1 : 11~20페이지, 2: 21~30페이지)
	private int curPageIndex;
	
	public QnaPageView() {
		
	}

	
	public QnaPageView(int totalPage, int numOfQnaPerPage, int selectPage, List<Qna> qnaList, int numOfPagePerPage, int curPageIndex) {
		this.totalPage = totalPage;
		this.numOfQnaPerPage = numOfQnaPerPage;
		this.selectPage = selectPage;
		this.qnaList = qnaList;
		this.numOfPagePerPage = numOfPagePerPage;
		this.curPageIndex=curPageIndex;
	}


	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getNumOfQnaPerPage() {
		return numOfQnaPerPage;
	}

	public void setNumOfQnaPerPage(int numOfQnaPerPage) {
		this.numOfQnaPerPage = numOfQnaPerPage;
	}

	public int getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
	}

	public List<Qna> getQnaList() {
		return qnaList;
	}

	public void setQnaList(List<Qna> qnaList) {
		this.qnaList = qnaList;
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

	
	
	
	
	
	
	
}
