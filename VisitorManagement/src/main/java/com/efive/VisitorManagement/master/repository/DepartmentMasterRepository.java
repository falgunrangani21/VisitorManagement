package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Departmentmaster;

@Transactional
@Repository
public interface DepartmentMasterRepository extends JpaRepository<Departmentmaster, Long>{
	
	@Query(value="SELECT d.departmentid,d.deptname,c.companyid,c.cname,l.locationid,l.branchname "
			+ " FROM departmentmaster AS d, companymaster AS c, locationmaster AS l "
			+ " WHERE d.companyid=c.companyid AND d.locationid=l.locationid AND d.active=1 "
			+ " AND (LOWER(deptname) LIKE LOWER('%' :sSearch '%') "
			+ " OR LOWER(cname) LIKE LOWER('%' :sSearch '%')" 
			+ " OR LOWER(branchname) LIKE LOWER('%' :sSearch '%'))", nativeQuery = true)
	public List<?> getDepartmentByQuery(Pageable pageable, @Param("sSearch") String sSearch);
}
