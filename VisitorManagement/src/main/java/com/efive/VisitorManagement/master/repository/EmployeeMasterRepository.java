package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Employeemaster;
@Transactional
@Repository
public interface EmployeeMasterRepository extends JpaRepository<Employeemaster,Long>{

	@Query(value="SELECT e.employeeid,e.empphoto,e.empname,e.emp_email,e.empcontactnumber,e.empgender," + 
			" (CASE WHEN e.empgender='m' THEN 'Male' ELSE 'Female' END) gender,  " + 
			" e.empqualification,e.designation,e.locationid,e.departmentid,c.cname,l.branchname,d.deptname,e.companyid " + 
			" FROM employeemaster AS e,companymaster AS c, locationmaster AS l,departmentmaster AS d" + 
			" WHERE e.companyid=c.companyid AND e.locationid=l.locationid AND e.departmentid=d.departmentid" +
			" AND e.active=1 " +
			" AND (LOWER(empname) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(emp_email) LIKE LOWER('%' :sSearch '%')" + 
			" OR (empcontactnumber) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(empqualification) LIKE LOWER('%' :sSearch '%')" +
			" OR LOWER(designation) LIKE LOWER('%' :sSearch '%')" +
			" OR LOWER(cname) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(branchname) LIKE LOWER('%' :sSearch '%')" +
			" OR LOWER(deptname) LIKE LOWER('%' :sSearch '%'))", nativeQuery = true)
	public List<?> getEmployeeByQuery(Pageable pageable, @Param("sSearch") String sSearch);
}
