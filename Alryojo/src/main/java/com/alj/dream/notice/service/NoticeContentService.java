package com.alj.dream.notice.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.file_notice.dao.NoticeFileDao;
import com.alj.dream.notice.dao.NoticeDao;
import com.alj.dream.notice.domain.NoticeContent;
@Service
public class NoticeContentService {
	@Autowired
	private SqlSessionTemplate sst;

	
	
	public NoticeContent getNotice(String notice_idx) {
		
		
		
		NoticeContent content= null;
		
		content=sst.getMapper(NoticeDao.class).getNotice(notice_idx);
		
		
		content.setFiles(sst.getMapper(NoticeFileDao.class).getFilesByNoticeIdx(notice_idx));
		
		
		
		
		return content;
	}
}
