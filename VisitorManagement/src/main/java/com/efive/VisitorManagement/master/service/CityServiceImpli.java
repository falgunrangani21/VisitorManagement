package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.efive.VisitorManagement.entity.Citymaster;
import com.efive.VisitorManagement.master.repository.CityMasterRepository;

@Service("CityService")
public class CityServiceImpli implements CityService{
	
	@Autowired
	private CityMasterRepository citymasterrepo;
	
	@Override
	public long countCitydata() {	
		return citymasterrepo.count();
	}

	@Override
	public boolean save(Citymaster citymaster) {
		citymasterrepo.save(citymaster);
		 return true;
	}

	@Override
	public List<?> getCityByQuery(Pageable pageable,String sSearch) {
		return citymasterrepo.getCityByQuery(pageable,sSearch);
	}

	@Override
	public Citymaster findById(Long id) {
		return citymasterrepo.getOne(id);
	}
}