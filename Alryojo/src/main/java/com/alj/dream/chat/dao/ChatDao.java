package com.alj.dream.chat.dao;


import java.util.List;

import com.alj.dream.chat.domain.Chat;
import com.alj.dream.chat.domain.ChatRoom;
public interface ChatDao {

	
	public int insertChat(Chat chat);
	public List<ChatRoom> selectChatRoomByIdx(String mytype, int myidx);
	public List<Chat> selectChatByMatchidx(int matchidx);
	public String selectCurTime();
}
