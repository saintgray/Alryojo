package com.alj.dream.post.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.file_post.dao.PostFilesDao;
import com.alj.dream.file_post.domain.PostFileRequest;
import com.alj.dream.post.dao.PostDao;
import com.alj.dream.post.domain.PostWriteRequest;
import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.MyProfileInfo;
import com.alj.dream.profile.domain.ProfileRequest;

@Service
public class PostWriteService {

	@Autowired
	private SqlSessionTemplate template;
	
	public int writePost(PostWriteRequest wRequest) {
		
		// 글 업로드 처리
		int resultCnt = insertPost(wRequest);
		System.out.println("insertPost: " + resultCnt);
		
		// 파일업로드 처리
		// post_idx 가져오기
		int post_idx = wRequest.getPost_idx();

		List<PostFileRequest> fileList = wRequest.getFileList();
		System.out.println(fileList);
				
		// 파일 업로드	
		if(!(fileList==null || fileList.isEmpty())) {
			
			for(PostFileRequest file : fileList) {
				file.setPost_idx(post_idx);
				template.getMapper(PostFilesDao.class).insertPostFile(file);
			}
			
		}
		
		return resultCnt;
	}
	
	public int insertPost(
			PostWriteRequest wRequest
			) {
		
		int resultCnt = 0;
		
		resultCnt = template.getMapper(PostDao.class).insertPost(wRequest);
		
		return resultCnt;
		
	}
	
	public MyProfileInfo getProfile(String m_idx) {
		return template.getMapper(ProfileDao.class).getProfile(m_idx);
	}
	
	public ProfileRequest getWriterProfile(int m_idx) {
		return template.getMapper(ProfileDao.class).getWriterProfile(m_idx);
	}
	
}
