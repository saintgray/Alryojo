package com.alj.dream.file_post.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.file_post.dao.PostFilesDao;
import com.alj.dream.file_post.domain.PostFileRequest;

@Service
public class PostFileUploadService {

	private PostFilesDao dao;

	@Autowired
	private SqlSessionTemplate template;

	public int insertPostFile(List<PostFileRequest> fileList) {

		int resultCnt = 0;

		dao = template.getMapper(PostFilesDao.class);

		for(int i=0; i<fileList.size(); i++) {
			resultCnt = dao.insertPostFile(fileList.get(i));
			
			if(resultCnt==0) {
				return 0;
			}
		}

		return resultCnt;

	}
	
	
	
}
