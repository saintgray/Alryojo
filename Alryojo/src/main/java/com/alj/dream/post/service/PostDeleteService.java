package com.alj.dream.post.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.post.dao.PostDao;

@Service
public class PostDeleteService {

	private PostDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int deletePost(int post_idx) {
		
		dao = template.getMapper(PostDao.class);
		
		return dao.deletePost(post_idx);
		
	}
	
}
