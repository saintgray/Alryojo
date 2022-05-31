package com.alj.dream.post.domain;

import java.util.List;

import com.alj.dream.file_post.domain.PostFileRequest;
import com.alj.dream.match.domain.MatchInfo;

public class PostViewRequest {
	
	private int post_idx;
	private String post_nm;
	private String post_content;
	private String post_regdate;
	private String post_editdate;
	private int cat_idx;
	private String cat_nm;
	private int m_idx;
	private int match_count;
	private String wanted;
	private List<MatchInfo> matchInfos;
	private List<PostFileRequest> fileList;
	
	public PostViewRequest() {}

	public int getPost_idx() {
		return post_idx;
	}

	public String getPost_nm() {
		return post_nm;
	}

	public String getPost_content() {
		return post_content;
	}

	public String getPost_regdate() {
		return post_regdate;
	}

	public String getPost_editdate() {
		return post_editdate;
	}

	public int getCat_idx() {
		return cat_idx;
	}

	public String getCat_nm() {
		return cat_nm;
	}

	public int getM_idx() {
		return m_idx;
	}

	public int getMatch_count() {
		return match_count;
	}

	public String getWanted() {
		return wanted;
	}
	
	public List<MatchInfo> getMatchInfos() {
		return matchInfos;
	}
	
	public void setMatchInfos(List<MatchInfo> matchInfos) {
		this.matchInfos = matchInfos;
	}

	public List<PostFileRequest> getFileList() {
		return fileList;
	}

	public void setFileList(List<PostFileRequest> fileList) {
		this.fileList = fileList;
	}
	
}
