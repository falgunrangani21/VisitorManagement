package com.efive.VisitorManagement.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Locationmaster;
import com.efive.VisitorManagement.master.repository.LocationRepository;

@Service("LocationService")
public class LocationServiceImpli implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public boolean save(Locationmaster locationmaster) {

		locationRepository.save(locationmaster);
		return true;
	}

	
	  @Override 
	  public Locationmaster findById(Long locationid) { 
	  return locationRepository.getOne(locationid);
	  }
	 
	
	@Override
	public List<?> getBranchByQuery(Pageable pageable, String sSearch) {
		// TODO Auto-generated method stub
		return locationRepository.getBranchByQuery(pageable, sSearch);
	}

	@Override
	public long countBranchdata() {
		return locationRepository.count();
	}

}
