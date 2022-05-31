package com.alj.dream.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alj.dream.chat.domain.ChatRoom;
import com.alj.dream.chat.service.ChatRoomListService;

@Controller
public class ChatRoomListController {

	@Autowired
	ChatRoomListService service;
	
	 @RequestMapping("/chat/chatlist") 
	 public String getChatList(Model model,
			 Authentication auth) {
	 
		 System.out.println("ChatListController:getChatList진입");
		 try {
		 model.addAttribute("list",service.getChatRoomList(auth));
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 System.out.println("ChatListController:getChatList return전");
		 
	  return "chat/chatlist";
	  }
	 
}
