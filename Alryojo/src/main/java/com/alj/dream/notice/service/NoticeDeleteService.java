package com.alj.dream.notice.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alj.dream.file_notice.dao.NoticeFileDao;
import com.alj.dream.file_notice.domain.NoticeFileInfo;
import com.alj.dream.notice.dao.NoticeDao;

@Service
public class NoticeDeleteService {

	@Autowired
	private SqlSessionTemplate sst;
	
	
	@Transactional
	public int deleteNotice(String notice_idx, HttpServletRequest req) {
		
		
		int result=0;
		
		result= sst.getMapper(NoticeDao.class).deleteNotice(notice_idx);
		
		
		// 서버에 있는 해당 공지사항을 idx로 가지는 모든 데이터를 DB에서 가져와서 공지사항 파일을 담은 서버 경로 폴더 안에 있는 파일들을 삭제한다.
		
		
		List<NoticeFileInfo> files= sst.getMapper(NoticeFileDao.class).getFilesByNoticeIdx(notice_idx);
		
		
		String path=req.getSession().getServletContext().getRealPath("/resources/files/notice/attachfiles");
		
		for(NoticeFileInfo file : files) {
			
			
			File delFile= new File(path, file.getFile_nm());
			
			if(delFile.exists()) {
				delFile.delete();
			}
		}
		
		return result;
		
		
		
		
	}
}
