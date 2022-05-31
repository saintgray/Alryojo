package com.alj.dream.chat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alj.dream.chat.domain.Chat;
import com.alj.dream.chat.service.ChatRoomService;

import com.alj.dream.match.domain.Match;




@Controller
public class ChatRoomController {

	@Autowired
	ChatRoomService service;
	
	// 채팅방으로 입장했을 때 : 필요한 데이터 보내주기
	@RequestMapping(value="/chat/chatroom", method=RequestMethod.GET)
	public ModelAndView intoChat(
			ModelAndView mv,
			@RequestParam("myidx") int myidx,
			@RequestParam("matchidx") int matchidx,
			@RequestParam("reciever") int reciever,
			HttpSession session,
			HttpServletRequest req
			) {
			
		System.out.println("ChatRoomController : intoChat진입");
		System.out.println(req.getHeader("referer"));
		System.out.println(req.getRequestURI());
		
		
		
		
		// 에코핸들러에서 사용하기 위해 필요한 정보들 세션에 저장.
		session.setAttribute("myIdx", myidx);			
		session.setAttribute("matchidx", matchidx);
		session.setAttribute("reciever", reciever);
		
		
		// 채팅 시 필요한 기본정보 보내주기
		mv.addObject("reciever", reciever);
		mv.addObject("matchidx", matchidx);
		
		
		// 채팅 시 필요한 정보 보내주기
		mv.addObject("matchidx", matchidx);
		mv.addObject("reciever", reciever);
		
		

		mv.addObject("recieverInfo",service.getMemInfo(reciever));
		
		Match match = service.getMatch(matchidx);
		mv.addObject("match", match);
		
		
		
		
		// 매칭취소가능여부
		mv.addObject("unmatchYN", service.getMatchDateDiff(matchidx, match.getMatch_date()));


		
		// 종현 수정 // getChat 메소드는 matchidx 파라미터만 필요하므로 쓸모없는 파라미터를 보내지 않습니다 
		
		// mv.addObject("chatlist", service.getChat(myIdx,matchidx,reciever));
		mv.addObject("chatlist", service.getChat(matchidx));

		
		// 신고가능여부와 후기작성여부
		mv.addObject("ableRprt", service.getAbleRprt(myidx, matchidx));
		mv.addObject("ableRv", service.getAbleReview(matchidx));
		
		
		return mv;
	}
	
	
	// 종현 : 채팅 메세지들 가져오기
	@GetMapping("/chat/room/{matchidx}")
	@ResponseBody
	public List<Chat> getChatList(@RequestParam String matchidx){
		
		
				
		System.out.println("getChatList()...");
		
		List<Chat> chatHistory=null;
		try {
		   chatHistory=service.getChat(Integer.parseInt(matchidx));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return chatHistory;
	}
	
	
	// 채팅메세지 DB에 저장하는 메소드
	@RequestMapping(value="/chat/insertchat", method=RequestMethod.POST)
	@ResponseBody
	public int insertChat(
			@RequestParam("postidx") int postidx,
			@RequestParam("m_reciever") int m_reciever,
			@RequestParam("m_sender") int m_sender,
			@RequestParam("mytype") String mytype,
			@RequestParam("msg") String msg,
			@RequestParam("sysMsgYN") String sysMsgYN
			){

		System.out.println("ChatRoomController : insertChat진입성공");
		//getmatchidx해야한다는 거죠
		//이것도 서비스에서 만들어서 하는게 나은거네
		Chat chat = service.getChatRequest(postidx,m_reciever,m_sender,mytype,msg,sysMsgYN);
		
		//서비스에서 채팅 바로 insert
		int resultCnt=service.insertChat(chat);
		
		return resultCnt;
	}
	
	
	// 채팅방 나갈때
	@RequestMapping(value="/chat/leavechat", method=RequestMethod.POST)
	@ResponseBody
	public int leaveChat(
			@RequestParam("matchidx") int matchidx,
			@RequestParam("myidx") int myidx,
			@RequestParam("reciever") int reciever,
			@RequestParam("mytype") String mytype
			) {
		System.out.println("ChatRoomController : leaveChat");
		
		System.out.println(mytype);
		
		
		int resultCnt = service.updateMatchOutyn(matchidx,myidx,reciever,mytype);
		
		return resultCnt;
	}
	
	// 채팅 매칭업데이트 할때
	@RequestMapping(value="/chat/matchupdate", method=RequestMethod.POST)
	@ResponseBody
	public int updateMatchYN(
			@RequestParam("matchYN") String matchYN,
			@RequestParam("matchidx") int matchidx
			) {
		System.out.println("ChatRoomController : updateMatchYN 진입");
		
		int result = service.updateMatchYN(matchYN, matchidx);
		
		return result;
	}
	
	
}
