package com.alj.dream.post.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.file_post.dao.PostFilesDao;
import com.alj.dream.file_post.domain.PostFileEditRequest;
import com.alj.dream.file_post.domain.PostFileRequest;
import com.alj.dream.post.dao.PostDao;
import com.alj.dream.post.domain.PostEditRequest;
import com.alj.dream.post.domain.PostWriteRequest;
import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.ProfileRequest;
import com.alj.dream.util.file.UploadFileUtil;

@Service
public class PostEditService {
	
	@Autowired
	private SqlSessionTemplate template;
	
	public PostWriteRequest getPost(int post_idx) {
		
		// 글 가져오기
		PostWriteRequest wRequest = template.getMapper(PostDao.class).selectWriteRequestByPostIdx(post_idx);

		// 첨부파일 가져오기
		List<PostFileRequest> fileList = template.getMapper(PostFilesDao.class).selectByPostIdx(post_idx);
		
		wRequest.setFileList(fileList);
				
		return wRequest;
	}

	public int editPost(PostEditRequest wRequest, String saveDir) {
		int resultCnt = 0;
		
		// 글 수정
		resultCnt = template.getMapper(PostDao.class).updatePost(wRequest);
		
		if(resultCnt>0) {
			// 파일 수정
			List<PostFileEditRequest> fileList = wRequest.getFileList();
			
			for(PostFileEditRequest attachfile : fileList) {
				if(attachfile.getUpdate_status().equalsIgnoreCase("E")) {
					// 파일 삭제, db에 deldate 추가
					String filename = attachfile.getFile_nm() + "." + attachfile.getFile_exet();
					
					UploadFileUtil.delete(saveDir, filename);
					
					System.out.println("파일 삭제");
					
					resultCnt = template.getMapper(PostFilesDao.class).deleteOneFile(attachfile.getPost_idx(), attachfile.getFile_nm());
					
				} else if(attachfile.getUpdate_status().equalsIgnoreCase("N")) {
					// 새로운 파일이라면 저장
					resultCnt = template.getMapper(PostFilesDao.class).insertPostFile(attachfile.getFileRequest());
					
				} else if(attachfile.getUpdate_status().equalsIgnoreCase("I")) {
					// 이미 존재하는 파일 -> 넘기기
					
				}
			}
			
		}
		
		return resultCnt;
	}
	
	public ProfileRequest getWriterProfile(int m_idx) {
		return template.getMapper(ProfileDao.class).getWriterProfile(m_idx);
	}
	
}
