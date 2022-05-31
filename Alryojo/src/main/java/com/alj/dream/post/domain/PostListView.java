package com.alj.dream.post.domain;

import java.util.List;

public class PostListView {
	
	private int totalPageCount;	// 총 페이지 수
	private int totalCount;		// 총 게시물 수
	private int countPerPage;	// 페이지 당 출력할 게시물 수
	private int currentPage;	// 현재 페이지 번호
	private List<PostListInfo> list;	// 출력할 방명록 게시물 리스트
	
	public PostListView(int totalCount, int countPerPage, int currentPage, List<PostListInfo> list) {
		this.totalCount = totalCount;
		this.countPerPage = countPerPage;
		this.currentPage = currentPage;
		this.list = list;
		calTotalPageCount();
	}
	
	private void calTotalPageCount() {
		totalPageCount = totalCount / countPerPage;
		if(totalCount%countPerPage>0) {
			totalPageCount++;
		}
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<PostListInfo> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "PostListView [totalPageCount=" + totalPageCount + ", totalCount=" + totalCount + ", countPerPage="
				+ countPerPage + ", currentPage=" + currentPage + ", list=" + list + "]";
	}

}