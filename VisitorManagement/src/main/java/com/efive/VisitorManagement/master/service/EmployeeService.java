package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.efive.VisitorManagement.entity.Employeemaster;

@Service
public interface EmployeeService {

public boolean save(Employeemaster employeemaster);
	
	public long countEmployeedata();
	
	public List<?> getEmployeeByQuery(Pageable pageable,String sSearch);

	public Employeemaster findById(Long employeeid);
}
