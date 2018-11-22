package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Companymaster;
@Transactional
@Repository
public interface CompanyMasterRepository extends JpaRepository<Companymaster, Long> {

	@Query(value="SELECT c.companyid,c.cname,c.cemail,c.cfax,c.ctelephonenumber,c.work_details,"
			+ " c.cno_ofemployee,c.cno_ofbranches,c.cwebsite,c.active "
			+ " FROM companymaster AS c WHERE c.active = 1 "
			+ " AND (LOWER(cname)  LIKE LOWER('%' :sSearch  '%') "
			+ " OR LOWER(cemail)  LIKE LOWER('%' :sSearch '%') "
			+ " OR ctelephonenumber  LIKE ('%' :sSearch '%') "						
			+ " OR LOWER(work_details) LIKE LOWER('%' :sSearch '%') "		
			+ " OR cno_ofbranches LIKE ('%' :sSearch '%') "
			+ " OR LOWER(cwebsite)   LIKE LOWER('%' :sSearch '%'))",nativeQuery = true)
	public List<?> getCompanyByQuery(Pageable pageable,@Param("sSearch") String sSearch);
	
}
