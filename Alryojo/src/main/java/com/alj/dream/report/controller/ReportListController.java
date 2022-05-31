package com.alj.dream.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alj.dream.report.service.ReportListService;


@Controller
@RequestMapping("/admin/reported/list")
public class ReportListController {

	@Autowired
	private ReportListService service;

	@GetMapping
	public String getReportInfo(Model model, String m_idx) {
		
		
		try {
		model.addAttribute("list", service.getReportListByIdx(m_idx));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "admin/report/reportedlist";
		
		
		
	}
}
