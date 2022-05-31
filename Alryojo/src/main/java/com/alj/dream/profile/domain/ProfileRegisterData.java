package com.alj.dream.profile.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProfileRegisterData {
	
	private int profile_idx;
	private String m_idx;
	private String line;
	private String calltime;
	private String career;
	private String qna;
	private List<MultipartFile> files;
	
	public ProfileRegisterData() {
		
	}

	public ProfileRegisterData(int profile_idx, String m_idx, String line, String calltime, String career, String qna,
			List<MultipartFile> files) {
		this.profile_idx = profile_idx;
		this.m_idx = m_idx;	
		this.line = line;
		this.calltime = calltime;
		this.career = career;
		this.qna = qna;
		this.files = files;
	}

	public int getProfile_idx() {
		return profile_idx;
	}

	public void setProfile_idx(int profile_idx) {
		this.profile_idx = profile_idx;
	}

	public String getM_idx() {
		return m_idx;
	}

	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCalltime() {
		return calltime;
	}

	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getQna() {
		return qna;
	}

	public void setQna(String qna) {
		this.qna = qna;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	
	
	
	
}
