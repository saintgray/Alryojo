package com.alj.dream.report.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.report.dao.ReportDao;
import com.alj.dream.report.domain.Report;

@Service
public class ReportService {

	private ReportDao dao;


	@Autowired 
	private SqlSessionTemplate template;


	// 하루 신고횟수 확인
	public int chkReport(int myidx) {

		dao = template.getMapper(ReportDao.class);

		int todayRprt = dao.todayReportByMyidx(myidx);	
		int resultCnt = 0;

		// 오늘 이회원의 신고횟수가 하루에 다섯번 이상이라면 (6번부터)더이상의 신고는 불가능하다.

		System.out.println("오늘 신고횟수 : "+todayRprt);
	
		if (todayRprt <5){ 
			resultCnt = 1;  //신고해도 괜찮은사람
		}else{
			resultCnt = 0;   //신고해도 안괜찮은 사람 
		}



		return resultCnt;
	}


	// 신고 등록할 때
		public int insertReport(Report r) {

			dao = template.getMapper(ReportDao.class);

			int resultCnt = dao.insertReport(r);

			return resultCnt;

		}

}

