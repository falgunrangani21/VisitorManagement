package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Employeemaster;
import com.efive.VisitorManagement.master.repository.EmployeeMasterRepository;

@Service("EmployeeService")
public class EmployeeServiceImpli implements EmployeeService {

	@Autowired
	private EmployeeMasterRepository erepository;

	@Override
	public boolean save(Employeemaster employeemaster) {
		erepository.save(employeemaster);
		return true;
	}

	@Override
	public long countEmployeedata() {
		
		return erepository.count();
	}

	@Override
	public List<?> getEmployeeByQuery(Pageable pageable, String sSearch) {
		
		return erepository.getEmployeeByQuery(pageable, sSearch);
	}

	@Override
	public Employeemaster findById(Long employeeid) {
		
		return erepository.getOne(employeeid);
	}
	
	
}
