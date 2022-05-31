package com.alj.dream.member.domain;

import java.util.List;


public class MemberListView {
	
	private int totalPage;
	private int numOfMemberPerPage;
	private int selectPage;
	private String searchType;
	private String keyword;
	private String blacklisted;
	private String sortBy;
	private String sortType;
	private List<MemberInfo> list;
	
	
	
	public MemberListView(int totalPage, int numOfMemberPerPage, int selectPage, String searchType, String keyword,
			String blacklisted, String sortBy, String sortType, List<MemberInfo> list) {
		this.totalPage = totalPage;
		this.numOfMemberPerPage = numOfMemberPerPage;
		this.selectPage = selectPage;
		this.searchType = searchType;
		this.keyword = keyword;
		this.blacklisted = blacklisted;
		this.sortBy = sortBy;
		this.sortType = sortType;
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNumOfMemberPerPage() {
		return numOfMemberPerPage;
	}
	public void setNumOfMemberPerPage(int numOfMemberPerPage) {
		this.numOfMemberPerPage = numOfMemberPerPage;
	}
	public int getSelectPage() {
		return selectPage;
	}
	public void setSelectPage(int selectPage) {
		this.selectPage = selectPage;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getBlacklisted() {
		return blacklisted;
	}
	public void setBlacklisted(String blacklisted) {
		this.blacklisted = blacklisted;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public List<MemberInfo> getList() {
		return list;
	}
	public void setList(List<MemberInfo> list) {
		this.list = list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
