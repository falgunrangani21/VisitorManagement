package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Departmentmaster;
import com.efive.VisitorManagement.master.repository.DepartmentMasterRepository;

@Service("DepartmentService")
public class DepartmentServiceImpli implements DepartmentService {

	@Autowired
	DepartmentMasterRepository drepository;

	@Override
	public boolean save(Departmentmaster departmentmaster) {
		drepository.save(departmentmaster);
		return true;
	}

	@Override
	public long countDepartmentdata() {

		return drepository.count();
	}

	@Override
	public List<?> getDepartmentByQuery(Pageable pageable, String sSearch) {
		return drepository.getDepartmentByQuery(pageable, sSearch);
	}

	@Override
	public Departmentmaster findById(Long departmentid) {

		return drepository.getOne(departmentid);
	}

}
