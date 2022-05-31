package com.alj.dream.match.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.match.dao.MatchDao;
import com.alj.dream.match.domain.MatchInfo;

@Service
public class MatchInfoService {
	
	private MatchDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public List<MatchInfo> getMatchInfos(int post_idx) {
		
		dao = template.getMapper(MatchDao.class);
		
		return dao.selectMatchInfo(post_idx);
	}

}
