package com.alj.dream.chat.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.chat.dao.ChatDao;
import com.alj.dream.chat.domain.Chat;
import com.alj.dream.match.dao.MatchDao;
import com.alj.dream.match.domain.Match;
import com.alj.dream.member.dao.MemberDao;
import com.alj.dream.member.domain.MemberInfo;
import com.alj.dream.report.dao.ReportDao;
import com.alj.dream.report.domain.Report;
import com.alj.dream.review.dao.ReviewDao;
import com.alj.dream.review.domain.Review;

@Service
public class ChatRoomService {

	private ChatDao dao;
	private MatchDao mdao;
	private MemberDao memdao;
	private ReviewDao rvdao;
	private ReportDao rpdao;
	
	@Autowired 
	private SqlSessionTemplate template;
	
	// DB에 채팅데이터 가져오기(이전대화)
	public List<Chat> getChat(int matchidx) {
		
		dao = template.getMapper(ChatDao.class);
		
		List<Chat> chatlist = dao.selectChatByMatchidx(matchidx);
		
		
		String sysmsgYN = "N";
		int size = chatlist.size();
		int i =0;
		int msglength = 0;


		while(i<size) {
			System.out.println(i+"번째채팅");
			//메세지가 15글자이상을 넘었는지 확인한다.
			msglength = chatlist.get(i).getMessage().length();

			if(msglength>15) {
				System.out.println("15글자넘음>>>");

				// 시스템메세지 확인여부용 스트링
				String distinctSYS = "###aljdream###";

				// 메세지에서 앞부분에 넣었을 확인여부용스트링을 찾아온다.
				String isitSYS = chatlist.get(i).getMessage().substring(0, 14);
				System.out.println("isitSYS :"+isitSYS);

				if(isitSYS.equalsIgnoreCase(distinctSYS)) {
					// 시스템 메세지여부=Y
					sysmsgYN = "Y";
					chatlist.get(i).setSysmsgYN(sysmsgYN);

					// 확인용메세지는 공백으로 바꿔준다.
					String msg = chatlist.get(i).getMessage().replace(distinctSYS, "");

					// 확인용메세지가 없는 일반메세지로 저장.
					chatlist.get(i).setMessage(msg);
					System.out.println(sysmsgYN + " : " + chatlist.get(i).getMessage());
				}else {
					sysmsgYN="N";
					chatlist.get(i).setSysmsgYN(sysmsgYN);
					System.out.println(sysmsgYN+" : "+chatlist.get(i).getMessage());
					
				}
			}else {
				sysmsgYN="N";
				chatlist.get(i).setSysmsgYN(sysmsgYN);
				System.out.println(sysmsgYN+" : "+chatlist.get(i).getMessage());
			}

			i++;
		}
		// 가져온 메세지가 시스템상메세지인지 아닌지 확인해서 보냄

		
		
		
		
		
		
		System.out.println("ChatRoomService : getChat :"+chatlist);
		return chatlist;
	}
	
	
	// DB에 match테이블 가져오기
	public Match getMatch(int matchidx) {
		
		mdao=template.getMapper(MatchDao.class);
		Match match = mdao.getMatchByMatchidx(matchidx);
		
		return match;
		
	}
	
	
	
	// DB에 채팅데이터 insert
	public int insertChat(Chat chat) {
		
		System.out.println(chat.getM_reciever());
		System.out.println(chat.getM_sender());
		System.out.println(chat.getMessage());
		System.out.println(chat.getMatch_idx());
		
		System.out.println("ChatRoomService : insertChat메소드");
		
		dao = template.getMapper(ChatDao.class);
		int resultCnt = dao.insertChat(chat);
		//System.out.println("Chat_IDX>>>".concat(String.valueOf(chat.getChat_idx())));
		
		System.out.println("chatinsert성공. 리턴합니다");
		
		return resultCnt;
	}

	

	// 채팅 나감을 표시
	public int updateMatchOutyn(int matchidx, int myidx, int reciever, String mytype) {
		
		mdao=template.getMapper(MatchDao.class);
		
		
		int menteeidx=0;
		int mentoridx=0;
		String outyn = null;
		int resultCnt = 0;
		
		
		if(mytype.equalsIgnoreCase("mentee")) {
			menteeidx = myidx;
			mentoridx = reciever;
		
			resultCnt = mdao.updateMatchOutyn(myidx, matchidx, menteeidx, mentoridx);
			
		}else if(mytype.equalsIgnoreCase("mentor")){
			menteeidx = reciever;
			mentoridx = myidx;
			
			resultCnt = mdao.updateMatchOutyn(myidx, matchidx, menteeidx, mentoridx);
			
		}
		return resultCnt;
	}

	
	
	// 매칭 가능여부 구하기
	public String getMatchDateDiff(int matchidx, String matchdate) {
		
		mdao = template.getMapper(MatchDao.class);
		
		int datediff = 0;
		String unmatchYN = null;
	
		if(matchdate == null) {
			// 매칭 날짜가 null인 것
			unmatchYN = "Y";
		}else {
			// 매칭한지 며칠 지났는지 구하기
			datediff = mdao.getMatchDateDiff(matchidx);
			
			if(datediff>3) {
				// 3일이 지나면 매칭취소가 불가능함
				unmatchYN = "N";
			}else if(datediff <=3) {
				// 3일이 지나지 않았다면 매칭취소가 가능함
				unmatchYN ="Y";
			}
		}
		return unmatchYN;
	}

	// 매칭 여부 업데이트
	public int updateMatchYN(String matchYN, int matchidx) {
		
		mdao = template.getMapper(MatchDao.class);
		
		int resultCnt = mdao.updateMatchYN(matchYN, matchidx);
		
		return resultCnt;
	}

	// 회원 정보 가져오기
	public MemberInfo getMemInfo(int myIdx) {
		
		memdao = template.getMapper(MemberDao.class);
		
		String m_idx = String.valueOf(myIdx);
		
		MemberInfo memInfo= memdao.getMemberInfo(m_idx);
		
		return memInfo;
	}

	// 리뷰 등록할 때
	public String selectCurTime() {
		
		dao= template.getMapper(ChatDao.class);
		String time = dao.selectCurTime();
		return time;
		
	}


	// 신고가능여부 확인하기(이 매칭에서 한번)
	public String getAbleRprt(int myidx, int matchidx) {
		
		rpdao = template.getMapper(ReportDao.class);
		
		Report report = rpdao.selectReportByMatchidx(myidx, matchidx);
		String ableRprt = null;
		
		
		// 이 리시버를 이 매칭에서 신고한 기록이 있다면 더이상의 신고는 불가능하다

				if(report != null) {
					ableRprt = "N";
				}else {
					ableRprt = "Y";
				}

				System.out.println("ableRprt:"+ableRprt);

				return ableRprt;
		
		
	}
	
	
	// 후기 가능여부 확인하기
		public String getAbleReview(int matchidx) {

			rvdao = template.getMapper(ReviewDao.class);

			
			Review review = rvdao.chkReview(matchidx);
			String ableReview = null;

		
			if(review == null) {
				ableReview = "Y";
			}else {
				ableReview = "N";
			}
			System.out.println("ableReview:"+ableReview);
			return ableReview;
		}

		// 문의하기 후, 채팅에 insert하기 위해서 필요한 정보를 정리하는 메서드
		public Chat getChatRequest(int postidx, int m_reciever, int m_sender, String mytype, String msg, String sysMsgYN) {
			
			System.out.println("ChatRoomService : getChatRequest메소드");
			

			mdao = template.getMapper(MatchDao.class);

			
			//matchidx가져와야함
			int menteeIdx = 0;
			int mentorIdx = 0;
			
			if (mytype.equalsIgnoreCase("mentee")) {				
				mentorIdx = m_reciever;
				menteeIdx = m_sender;
			}else if(mytype.equalsIgnoreCase("mentor")) {
				menteeIdx = m_reciever;
				mentorIdx = m_sender;
			}
			System.out.println("menteeIdx : "+menteeIdx);
			System.out.println("mentorIdx : "+mentorIdx);
			System.out.println("postidx : "+postidx);

			
			Match match= mdao.selectMatch(postidx, menteeIdx, mentorIdx);	
			System.out.println("매칭테이블가져오기성공 : "+match);
			
			int matchidx = match.getMatch_idx();
			
			Chat chat =new Chat(matchidx, null, m_sender, m_reciever, msg, sysMsgYN);
			
			System.out.println("getChatRequest성공. 리턴합니다");
			
			return chat;
		}
	

	
}
