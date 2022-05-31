package com.alj.dream.post.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.post.dao.PostDao;
import com.alj.dream.post.domain.PostViewRequest;
import com.alj.dream.profile.dao.ProfileDao;
import com.alj.dream.profile.domain.ProfileRequest;
import com.alj.dream.file_post.dao.PostFilesDao;
import com.alj.dream.file_post.domain.PostFileRequest;
import com.alj.dream.match.dao.MatchDao;
import com.alj.dream.match.domain.MatchInfo;

@Service
public class PostViewService {

	@Autowired
	private SqlSessionTemplate template;
	
	public PostViewRequest viewPost(int post_idx) {
		
		PostViewRequest viewRequest = template.getMapper(PostDao.class).selectPostByPostIdx(post_idx);
		
		if (viewRequest != null) {
			// 첨부파일 가져오기
			List<PostFileRequest> fileList = template.getMapper(PostFilesDao.class).selectByPostIdx(post_idx);
			System.out.println(fileList);
			
			viewRequest.setFileList(fileList);

			// 생성된 채팅이 있을 경우 채팅정보 가져오기
			if (viewRequest.getMatch_count()>0) {

				List<MatchInfo> matchInfos = getMatchInfos(post_idx);
				System.out.println(matchInfos);
				
				viewRequest.setMatchInfos(matchInfos);

			}

		}
		
		return viewRequest;
		
	}
	
	public ProfileRequest getWriterProfile(int m_idx) {
		return template.getMapper(ProfileDao.class).getWriterProfile(m_idx);
	}
	
	public List<MatchInfo> getMatchInfos(int post_idx) {
		return template.getMapper(MatchDao.class).selectMatchInfo(post_idx);
	}
	
}
