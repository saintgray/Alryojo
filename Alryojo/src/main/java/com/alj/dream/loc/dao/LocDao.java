package com.alj.dream.loc.dao;

import java.util.List;

import com.alj.dream.loc.domain.Location;

public interface LocDao {
	
	List<Location> getLocations(String m_idx);

}
