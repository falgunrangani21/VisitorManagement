package com.efive.VisitorManagement.master.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.efive.VisitorManagement.entity.Companymaster;

public interface CompanyService {

	public boolean save(Companymaster companymaster);
	public Companymaster findById(Long companyid);
	public List<?> getCompanyByQuery(Pageable pageable,String sSearch);
	public long countCompanydata();
	
}
