package com.alj.dream.match.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.match.dao.MatchDao;
import com.alj.dream.match.domain.Match;


@Service
public class MatchChkService {
	
	// 게시글에서 문의하기를 누름>> ajax통신
	// myidx,(나), midx(글쓴이), postidx, wanted를 넘겨받아 match테이블의 존재유무 확인.
	// 없으면 match테이블 생성 후 결과 리턴
	
	private MatchDao dao;
	@Autowired 
	private SqlSessionTemplate template;
	
	
	public int chkMatch(int postidx, int midx, int myidx, String wanted) {
			
			System.out.println("MatchChkService : chkMatch진입성공");
		
			dao = template.getMapper(MatchDao.class);
			
			int menteeIdx = 0;
			int mentorIdx = 0;
			
			if (wanted.equalsIgnoreCase("mentee")) {				
				mentorIdx = midx;
				menteeIdx = myidx;
			}else if(wanted.equalsIgnoreCase("mentor")) {
				menteeIdx = midx;
				mentorIdx = myidx;
			}
			System.out.println("menteeIdx : "+menteeIdx);
			System.out.println("mentorIdx : "+mentorIdx);
			
			
			Match match=dao.selectMatch(postidx, menteeIdx, mentorIdx);	
			System.out.println("매칭테이블가져오기성공 : "+match);
		
			int resultCnt = 0;		
			
			if(!(match == null)) {
				// 비어있지않다면
				int matchIdx = match.getMatch_idx();
				System.out.println(matchIdx);
				resultCnt=1;
			}else {
				// 비어있다면
				int insertRslt = dao.insertMatch(postidx, menteeIdx, mentorIdx);
				if(insertRslt==1) {
					// insert성공
					resultCnt=0;
				}else{
					// insert실패
					resultCnt=2;
				}
			}
			
			return resultCnt;
		}
	

}
