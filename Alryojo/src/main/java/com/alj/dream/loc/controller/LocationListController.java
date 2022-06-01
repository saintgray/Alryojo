package com.alj.dream.loc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alj.dream.loc.domain.Location;
import com.alj.dream.loc.service.LocationListService;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationListController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private LocationListService locService;

	public LocationListController() {

	}

	@Autowired
	public LocationListController(LocationListService locService) {
		this.locService = locService;
	}

	@GetMapping
	public ResponseEntity<?> getLocations(HttpServletRequest req) {
		logger.info("+ initiated getLocations methods..");
		ResponseEntity<?> resp = null;
		try {
			List<Location> list = null;
			list = locService.getLocations(req.getParameter("memberIdx"));
			resp = ResponseEntity.ok(list);
		} catch (Exception e) {
			resp = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return resp;
	}

}
