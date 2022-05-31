package sockethandler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alj.dream.chat.domain.Chat;
import com.alj.dream.chat.service.ChatRoomService;
import com.google.gson.Gson;


public class EchoHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	private Map<Integer, WebSocketSession> sessionMap = new HashMap<Integer, WebSocketSession>();


	@Autowired
	private ChatRoomService service;


	// 채팅창에 들어왔을때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("(Original)myIdx>>>"+session.getAttributes().get("myIdx"));
		//String myIdx = String.valueOf(session.getAttributes().get("myIdx"));
		int myidx = (int)session.getAttributes().get("myIdx");
		
		//확인용
		System.out.println("(Integer)myIdx>>>"+myidx);
		
		sessionMap.put(myidx, session);										
		
		logger.info("연결되었습니다", session.getId());		
		System.out.println("채팅창입장: " + myidx);
		System.out.println("sessionMap에 저장 : " + myidx);	
		
	}

	
	// 채팅메세지 전달
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		
		
		// chatDB에 저장할 데이터들
		int myidx = (int)session.getAttributes().get("myIdx");
		int matchIdx = (int)session.getAttributes().get("matchidx");
		int recieverIdx = (int)session.getAttributes().get("reciever");		// 전달받을 상대방idx
		
		
		System.out.println("recieverIdx"+recieverIdx);
		
		logger.info("{}로부터 {}를 전달받았습니다", myidx, message.getPayload());		// 입력해서 sendMessage(웹소켓으로 메세지를 보내려던)에서 받은 메세지
		Gson gson = new Gson();
		Chat chat = gson.fromJson(message.getPayload(), Chat.class);
		
		
		// chat객체에 데이터저장
		chat.setMatch_idx(matchIdx);
		chat.setM_sender(myidx);
		chat.setM_reciever(recieverIdx);
		
		// chat의 시간도 여기서 저장을 해야할 듯
		
		//chat.setSent(sent);
		int result = service.insertChat(chat);	// DB에 chat데이터 저장하는 메소드실행
		
		
		
		
		
		WebSocketSession ws = sessionMap.get(recieverIdx); 	// 전달할 세션?
		System.out.println("WebSocketSession ws = sessionMap.get(recieverIdx) 확인");
		
		// 전달 메세지
		TextMessage sendMsg = new TextMessage(gson.toJson(chat));
		
		session.sendMessage(sendMsg);	//나에게 메세지전달
		if(ws!=null) {
			ws.sendMessage(sendMsg); 	// 상대방에 메세지 전달
		}
				
		
		
		// 보낼 때 컨트롤러에서 필요로하는 postidx랑 midx를 보내준다?
	}

	// 채팅창에 나갔을때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		int myIdx = (int) session.getAttributes().get("myIdx");
		
		sessionMap.remove(myIdx);		// 나간 사람의 세션이 삭제
		
		logger.info("{} 창을 나감", myIdx);
		
		System.out.println("채팅창나감"+myIdx);
		
		
		
	}
	
	
}