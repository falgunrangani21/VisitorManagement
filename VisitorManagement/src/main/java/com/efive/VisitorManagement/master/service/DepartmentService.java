package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.efive.VisitorManagement.entity.Departmentmaster;

@Service
public interface DepartmentService {
	
	public boolean save(Departmentmaster departmentmaster);
	
	public long countDepartmentdata();
	
	public List<?> getDepartmentByQuery(Pageable pageable,String sSearch);

	public Departmentmaster findById(Long departmentid);

}
