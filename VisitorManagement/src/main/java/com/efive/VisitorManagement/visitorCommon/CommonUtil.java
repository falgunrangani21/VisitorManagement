package com.efive.VisitorManagement.visitorCommon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.master.service.CommonUtilService;

@Service
public class CommonUtil {

	@Autowired
	private CommonUtilService service;

	public List<?> getCountryData(Long id) {
		return service.getCountryData(id);
	}

	public List<?> getStateData(Long id) {
		return service.getStateData(id);
	}

	public List<?> getCityData(Long stateid, Long countryid) {
		return service.getCityData(stateid, countryid);
	}

	public List<?> getCompanyData(Long id) {
		return service.getCompanyData(id);
	}

	public List<?> getPersonData(Long locationid) {
		return service.getPersonData(locationid);
	}

	public List<?> getLocationData(Long companyid) {
		return service.getLocationData(companyid);
	}
	
	public List<?> getDepartmentData(Long companyid, Long locationid){
		return service.getDepartmentData(companyid, locationid);
	}
	
	public List<?> getVisitorCard(Long id){
		return service.getVisitorCard(id);
	}
}