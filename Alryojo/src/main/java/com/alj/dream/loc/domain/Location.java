package com.alj.dream.loc.domain;

public class Location {
	
	
	private String loc_idx;
	private String loc_nm;
	
	
	public Location() {
	
	}


	public Location(String loc_idx, String loc_nm) {
		this.loc_idx = loc_idx;
		this.loc_nm = loc_nm;
	}


	public String getLoc_idx() {
		return loc_idx;
	}


	public void setLoc_idx(String loc_idx) {
		this.loc_idx = loc_idx;
	}


	public String getLoc_nm() {
		return loc_nm;
	}


	public void setLoc_nm(String loc_nm) {
		this.loc_nm = loc_nm;
	}
	
	
	
	
	

}
