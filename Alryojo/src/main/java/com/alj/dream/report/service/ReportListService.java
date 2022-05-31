package com.alj.dream.report.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.report.dao.ReportDao;
import com.alj.dream.report.domain.ReportInfo;

@Service
public class ReportListService {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public List<ReportInfo> getReportListByIdx(String m_idx) {
		
		List<ReportInfo> repList=null;
		repList=sst.getMapper(ReportDao.class).getReportListByIdx(m_idx);
		for(ReportInfo inf: repList) {
			System.out.println(inf.getM_email());
			System.out.println(inf.getM_photo());
			System.out.println(inf.getM_report());
			System.out.println(inf.getMatch_idx());
		}
		return repList;
		
	}
}
