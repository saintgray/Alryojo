package com.alj.dream.qna.service;

import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.alj.dream.qna.dao.QnaDao;
import com.alj.dream.qna.domain.PageRequest;
import com.alj.dream.qna.domain.Qna;
import com.alj.dream.qna.domain.QnaPageView;
import com.alj.dream.reply.dao.ReplyDao;

import security.AccountDetails;

@Service
public class QnaListService {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public QnaPageView getQnaList(PageRequest pagereq, Authentication auth) {
		
		int curPageIndex=pagereq.getCurPageIndex();
		int numOfPagePerPage= pagereq.getNumOfPagePerPage();
		
		int selectPage = curPageIndex==-1? pagereq.getSelectPage() : numOfPagePerPage*(curPageIndex)+1;
		//선택한페이지 = PageRequest의 getSelectPage가 null과 같다면 1의값을 넣어주고 값이 있다면 문자열을 정수로 변환해서 넣어줌
		int numOfQnaPerPage = pagereq.getNumOfQnaPerPage()==0? 5: pagereq.getNumOfQnaPerPage();
		//페이지에 보여줄qna갯수 = PageRequest의 getNumOfQnaPerPage가 null가 같다면 5의값을 넣어주고 값이 있다면 문자열을 정수로 변환해서 넣어줌
		
		
		// 요청한 사람이 관리자인이 아닌지 확인.
		AccountDetails logininfo = (AccountDetails)auth.getPrincipal();
		Iterator<GrantedAuthority> itr = logininfo.getAuthorities().iterator();
		while(itr.hasNext()) {
			if(itr.next().getAuthority().equals("ROLE_GENERAL")) {
				pagereq.setM_idx(logininfo.getM_idx());;
				break;
			}
		}
		
		
		
		
		
		int selectPageFirstIndex = numOfQnaPerPage *(selectPage-1);
		//페이지의첫번쨰 인덱스 = 페이지에 보여줄qna갯수 *(선택한페이지-1)
		pagereq.setSelectPageFirstIndex(selectPageFirstIndex);
		
		
		
		int totalNumOfQna = sst.getMapper(QnaDao.class).getTotalNumOfQna(pagereq.getM_idx());
		//총qna의 갯수 = 문자열을 정수로 변환해 QnaDao에 매칭된 sql문을 매퍼가 실행.
		
		int totalPage = totalNumOfQna/numOfQnaPerPage;
		// 총페이지 = 총qna의 갯수/페이지에 보여줄 qna갯수
		totalPage = totalNumOfQna%numOfQnaPerPage==0?totalPage:totalPage+1;
		//총페이지 = 총qna의 갯수를 한 페이지에 보여줄 qna 갯수로 나눈 나머지가 0과 같다면 총페이지=총페이지  아니라면 총페이지=총페이지+1
		
		System.out.println("selectpage>>>"+String.valueOf(selectPage));
		
		// limit ?, ? >> 1번쨰 ? = selectPageFirstIndex ,2번째 ? = numOfQnaPerPage
		List<Qna> list = sst.getMapper(QnaDao.class).getQnaList(pagereq);
		
		if(pagereq.getM_idx()!=null) {
			
			for(Qna qna:list) {
				qna.setReplyInfo(sst.getMapper(ReplyDao.class).getReplyByQnaIdx(qna.getQna_idx()));
			}
			
		}
		
		
		// QnaPageView의 curPageIndex 변수에 값을 넣어 줄시 주의점
		// ex) 21~30 페이지의 curPageIndex =2 이다.
		// 만약 30 페이지를 클릭했다면 curPageIndex 가 3이 들어가서 21~30페이지가 아닌 31~40페이지가 보여질 것이다.
		// selectPage를 10으로 나눈 나머지가 0일때는 selectPage/10 의 값에서 1을 빼서 값을 넣어준다.
		curPageIndex = selectPage%10==0?(selectPage/10)-1:selectPage/10;
		
		QnaPageView pageView =new QnaPageView(totalPage, numOfQnaPerPage, selectPage, list, numOfPagePerPage, curPageIndex);
		return pageView;
		
	}
}
