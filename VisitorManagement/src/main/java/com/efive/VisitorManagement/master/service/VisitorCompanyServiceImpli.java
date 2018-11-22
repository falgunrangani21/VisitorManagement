package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Visitorcompanymaster;
import com.efive.VisitorManagement.master.repository.VisitorCompanyRepository;

@Service("VisitorCompanyService")
public class VisitorCompanyServiceImpli implements VisitorCompanyService {

	@Autowired
	private VisitorCompanyRepository vcompanyrepository;

	@Override
	public boolean save(Visitorcompanymaster vcompanymaster) {
		vcompanyrepository.save(vcompanymaster);
		return true;
	}

	@Override
	public long countVisitorCompanydata() {

		return vcompanyrepository.count();
	}

	@Override
	public List<?> getVisitorCompanyByQuery(Pageable pageable, String sSearch) {
		return vcompanyrepository.getVisitorCompanyByQuery(pageable, sSearch);
	}

	@Override
	public Visitorcompanymaster findById(Long vcompanyid) {
		return vcompanyrepository.getOne(vcompanyid);
	}

}
