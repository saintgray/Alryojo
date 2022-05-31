package com.alj.dream.file_notice.dao;

import java.util.List;

import com.alj.dream.file_notice.domain.NoticeFileInfo;

public interface NoticeFileDao {
	
	int insertNoticeFiles(List<NoticeFileInfo> noticeFileInfo);
	
	List<NoticeFileInfo> getFilesByNoticeIdx(String notice_idx);

}
