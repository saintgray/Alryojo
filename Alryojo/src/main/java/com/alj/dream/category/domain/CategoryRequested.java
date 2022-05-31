package com.alj.dream.category.domain;

import java.util.List;

public class CategoryRequested {
	
	private List<String> interest;
	
	
	public CategoryRequested() {
		
	}

	public CategoryRequested(List<String> interest) {
		
		this.interest = interest;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	
	

}
