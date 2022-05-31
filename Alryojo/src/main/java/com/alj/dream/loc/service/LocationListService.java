package com.alj.dream.loc.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alj.dream.loc.dao.LocDao;
import com.alj.dream.loc.domain.Location;

@Service
public class LocationListService {
	
	private SqlSessionTemplate sst;
	
	public LocationListService() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public LocationListService(SqlSessionTemplate sst) {
		this.sst = sst;
	}
	
	
	public List<Location> getLocations(String m_idx){
		return sst.getMapper(LocDao.class).getLocations(m_idx);
		
	}
	
}
