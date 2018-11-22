package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.master.repository.CommonUtilRepository;

@Service("CommonUtilService")
public class CommonUtilServiceImpli implements CommonUtilService {

	@Autowired
	private CommonUtilRepository commonutil;

	@Override
	public List<?> getCountryData(Long id) {
		return commonutil.getCountryData(id);
	}

	@Override
	public List<?> getStateData(Long id) {
		return commonutil.getStateData(id);
	}

	@Override
	public List<?> getCityData(Long stateid, Long countryid) {

		return commonutil.getCityData(stateid, countryid);
	}

	@Override
	public List<?> getCompanyData(Long id) {

		return commonutil.getCompanyData(id);
	}

	@Override
	public List<?> getPersonData(Long locationid) {
		return commonutil.getPersonData(locationid);
	}

	@Override
	public List<?> getLocationData(Long companyid) {
		return commonutil.getLocationData(companyid);
	}

	@Override
	public List<?> getDepartmentData(Long companyid, Long locationid) {
		return commonutil.getDepartmentData(companyid, locationid);
	}

	@Override
	public List<?> getVisitorCompanyData(Long vcompanyid) {

		return commonutil.getVisitorCompanyData(vcompanyid);
	}

	@Override
	public List<?> getEmployeeData(Long companyid, Long locationid, Long departmentid) {

		return commonutil.getEmployeeData(companyid, locationid, departmentid);
	}

	@Override
	public List<?> getVisitorLocation(Long vcompanyid) {
		return commonutil.getVisitorLocation(vcompanyid);
	}

	@Override
	public List<?> getVisitorCard(Long id) {
		return commonutil.getVisitorCard(id);
	}

	@Override
	public List<?> getCurrentVisitorByDate() {
		return commonutil.getCurrentVisitorByDate();
	}
}