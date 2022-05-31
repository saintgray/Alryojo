package com.alj.dream.notice.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.notice.dao.NoticeDao;
import com.alj.dream.notice.domain.Notice;
import com.alj.dream.notice.domain.NoticePageView;
import com.alj.dream.notice.domain.PageRequest;

@Service
public class NoticeListService {
	
	@Autowired
	private SqlSessionTemplate sst;

	
	public NoticePageView getNoticeList(PageRequest pagereq){
		int selectPage= pagereq.getSelectPage()==null? 1: Integer.parseInt(pagereq.getSelectPage());
		int numOfNoticesPerPage =  pagereq.getNumOfNoticesPerPage()==null? 5:Integer.parseInt(pagereq.getNumOfNoticesPerPage());
		
		
		int firstNoticeIndexOfSelectedPage = numOfNoticesPerPage*(selectPage-1);
		int totalNumOfNotices=Integer.parseInt(sst.getMapper(NoticeDao.class).getTotalNumOfNotice(pagereq.getKeyword()));
		int totalPage=totalNumOfNotices/numOfNoticesPerPage;
		totalPage=totalNumOfNotices%numOfNoticesPerPage==0?totalPage:totalPage+1;
		
		List<Notice> list = sst.getMapper(NoticeDao.class).getNoticeList(firstNoticeIndexOfSelectedPage, numOfNoticesPerPage,pagereq.getKeyword());
		
		//////////////////////////////////////////////////
		NoticePageView pageView= 
				new NoticePageView(totalPage, 
								   numOfNoticesPerPage, 
   								   selectPage,
   								   pagereq.getKeyword(),
								   list);
		//////////////////////////////////////////////////
		return pageView;
	}
}
