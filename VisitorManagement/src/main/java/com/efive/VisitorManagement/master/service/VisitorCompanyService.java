package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.efive.VisitorManagement.entity.Visitorcompanymaster;

public interface VisitorCompanyService {
	
	public boolean save(Visitorcompanymaster vcompanymaster);
	
	public long countVisitorCompanydata();
	
	public List<?> getVisitorCompanyByQuery(Pageable pageable,String sSearch);

	public Visitorcompanymaster findById(Long vcompanyid);
}