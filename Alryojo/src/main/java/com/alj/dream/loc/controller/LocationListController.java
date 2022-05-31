package com.alj.dream.loc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.loc.domain.Location;
import com.alj.dream.loc.service.LocationListService;

@RestController
@RequestMapping("/loc/list")
// @CrossOrigin(origins = "http://localhost:3000")
public class LocationListController {
	
	private LocationListService locService;
	
	public LocationListController() {
		
	}
	
	
	@Autowired
	public LocationListController(LocationListService locService) {
		this.locService = locService;
	}


	@PostMapping
	public List<Location> getLocations(String m_idx){
		
		List<Location> list =null;
		try {
			list=locService.getLocations(m_idx);
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	

}
