package com.alj.dream.chat.domain;

public class Chat {
	
	// 채팅 시 생성되는 채팅테이블 데이터
	
	private int match_idx;	// 매칭(채팅방) 고유번호
	private String sent;	// 보낸일자
	private int m_sender;	// 보낸사람idx
	private int m_reciever;	// 받는사람idx
	private String message;	// 채팅내용
	private String sysmsgYN;// 시스템메세지여부
	
	

	public Chat() {	}

	

	public Chat(int match_idx, String sent, int m_sender, int m_reciever, String message, String sysmsgYN) {
		this.match_idx = match_idx;
		this.sent = sent;
		this.m_sender = m_sender;
		this.m_reciever = m_reciever;
		this.message = message;
		this.sysmsgYN = sysmsgYN;
	}


	
	public int getMatch_idx() {
		return match_idx;
	}

	public void setMatch_idx(int match_idx) {
		this.match_idx = match_idx;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public int getM_sender() {
		return m_sender;
	}

	public void setM_sender(int m_sender) {
		this.m_sender = m_sender;
	}

	public int getM_reciever() {
		return m_reciever;
	}

	public void setM_reciever(int m_reciever) {
		this.m_reciever = m_reciever;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

	public String getSysmsgYN() {
		return sysmsgYN;
	}



	public void setSysmsgYN(String sysmsgYN) {
		this.sysmsgYN = sysmsgYN;
	}



	@Override
	public String toString() {
		return "Chat [match_idx=" + match_idx + ", sent=" + sent + ", m_sender=" + m_sender + ", m_reciever="
				+ m_reciever + ", message=" + message + ", sysmsgYN=" + sysmsgYN + "]";
	}
	
	

	
	
}
