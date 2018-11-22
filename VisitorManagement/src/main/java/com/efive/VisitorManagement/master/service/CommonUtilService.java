package com.efive.VisitorManagement.master.service;

import java.util.List;

public interface CommonUtilService {

	public List<?> getCountryData(Long id);
	
	public List<?> getStateData(Long id);
	
	public List<?> getCityData(Long stateid,Long countryid);
	
	public List<?> getCompanyData(Long id);
	
	public List<?> getPersonData(Long Locationid);
	
	public List<?> getLocationData(Long companyid);
	
	public List<?> getDepartmentData(Long companyid,Long locationid);
	
	public List<?> getVisitorCompanyData(Long vcompanyid);
	
	public List<?> getEmployeeData(Long companyid,Long locationid,Long departmentid);
	
	public List<?> getVisitorLocation(Long vcompanyid);
	
	public List<?> getVisitorCard(Long id);
	
	public List<?> getCurrentVisitorByDate();
}
