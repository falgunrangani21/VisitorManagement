package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Citymaster;

@Service
public interface CityService {

	public boolean save(Citymaster citymaster);
	
	public long countCitydata();
	
	public List<?> getCityByQuery(Pageable pageable,String sSearch);

	public Citymaster findById(Long cityid);
}