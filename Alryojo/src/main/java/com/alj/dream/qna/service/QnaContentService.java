package com.alj.dream.qna.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.qna.dao.QnaDao;
import com.alj.dream.qna.domain.QnaListContent;

@Service
public class QnaContentService {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public QnaListContent getQna(String qna_idx) {
		return sst.getMapper(QnaDao.class).getQna(qna_idx);
	}
}
