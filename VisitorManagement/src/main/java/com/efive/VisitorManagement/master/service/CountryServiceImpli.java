package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Countrymaster;
import com.efive.VisitorManagement.master.repository.CountryMasterRepository;

@Service("CountryService")
public class CountryServiceImpli implements CountryService{

	@Autowired
	private CountryMasterRepository countryrepository;
	
	@Override
	public Boolean save(Countrymaster countrymaster) {
		countryrepository.save(countrymaster);
		return true;
	}

	@Override
	public Countrymaster findById(Long id) {
		return countryrepository.getOne(id);
	}

	@Override
	public Long countCountrydata() {
		
		return countryrepository.count();
	}

	@Override
	public List<?> getCountryByQuery(Pageable pageable, String sSearch) {		
		return countryrepository.getCountryByQuery(pageable,sSearch);
	}
}