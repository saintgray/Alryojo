package com.alj.dream.chat.domain;

import com.alj.dream.member.domain.MemberInfo;

public class ChatRoom {

	// 채팅방에 필요한  데이터들
	
	private int match_idx;
	private String match_yn;
	private int sender_idx;
	private int reciever_idx;
	private String cat_type;
	private String cat_nm;
	private MemberInfo memberInfo;
	
	
	
	
	
	public ChatRoom() {}

	public ChatRoom(int match_idx, String match_yn, int sender_idx, int reciever_idx, String cat_type, String cat_nm,
			MemberInfo memberInfo) {
		this.match_idx = match_idx;
		this.match_yn = match_yn;
		this.sender_idx = sender_idx;
		this.reciever_idx = reciever_idx;
		this.cat_type = cat_type;
		this.cat_nm = cat_nm;
		this.memberInfo = memberInfo;
	}

	

	

	public int getMatch_idx() {
		return match_idx;
	}

	public void setMatch_idx(int match_idx) {
		this.match_idx = match_idx;
	}

	public String getMatch_yn() {
		return match_yn;
	}

	public void setMatch_yn(String match_yn) {
		this.match_yn = match_yn;
	}

	public int getSender_idx() {
		return sender_idx;
	}

	public void setSender_idx(int sender_idx) {
		this.sender_idx = sender_idx;
	}

	public int getReciever_idx() {
		return reciever_idx;
	}

	public void setReciever_idx(int reciever_idx) {
		this.reciever_idx = reciever_idx;
	}

	public String getCat_type() {
		return cat_type;
	}

	public void setCat_type(String cat_type) {
		this.cat_type = cat_type;
	}

	public String getCat_nm() {
		return cat_nm;
	}

	public void setCat_nm(String cat_nm) {
		this.cat_nm = cat_nm;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}

	@Override
	public String toString() {
		return "ChatRoom [match_idx=" + match_idx + ", match_yn=" + match_yn + ", sender_idx=" + sender_idx
				+ ", reciever_idx=" + reciever_idx + ", cat_type=" + cat_type + ", cat_nm=" + cat_nm + ", memberInfo="
				+ memberInfo + "]";
	}
	
	
	
	
}
