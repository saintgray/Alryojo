package com.alj.dream.report.dao;

import java.util.List;

import com.alj.dream.report.domain.Report;
import com.alj.dream.report.domain.ReportInfo;

public interface ReportDao {
	
	List<ReportInfo> getReportListByIdx(String m_idx);
	int insertReport(Report r);
	Report selectReportByMatchidx(int myidx, int matchidx);
	int todayReportByMyidx(int myidx);
}
