package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Companymaster;
import com.efive.VisitorManagement.master.repository.CompanyMasterRepository;

@Service("companyService")
public class CompanyServiceImpli implements CompanyService {

	@Autowired
	private CompanyMasterRepository companyRepo;

	@Override
	public boolean save(Companymaster companymaster) {
		companyRepo.save(companymaster);
		return true;
	}

	@Override
	public List<?> getCompanyByQuery(Pageable pageable, String sSearch) {

		return companyRepo.getCompanyByQuery(pageable, sSearch);
	}

	@Override
	public Companymaster findById(Long companyid) {

		return companyRepo.getOne(companyid);
	}

	@Override
	public long countCompanydata() {

		return companyRepo.count();
	}

}
