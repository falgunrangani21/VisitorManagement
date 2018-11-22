package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.efive.VisitorManagement.entity.Countrymaster;


public interface CountryService {

	public Boolean save(Countrymaster countrymaster);
	
	public Countrymaster findById(Long id);
	
	public Long countCountrydata();
	
	public List<?> getCountryByQuery(Pageable pageable, String sSearch);
}
