package com.alj.dream.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.home.domain.HomeInfo;
import com.alj.dream.home.service.HomeInfoService;

@RestController
@RequestMapping("/home/infos")
public class HomeInfoController {
	
	private HomeInfoService hiService;
	
	public HomeInfoController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public HomeInfoController(HomeInfoService hiService) {
		this.hiService = hiService;
	}
	
	@PostMapping
	public HomeInfo getHomeInfos() {
		
		return hiService.getMainInfos();
	}
	
	

}
