package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Visitorcompanymaster;

@Transactional
@Repository
public interface VisitorCompanyRepository extends JpaRepository<Visitorcompanymaster, Long> {
	
	@Query(value="SELECT vc.vcompanyid,vc.vcname,vc.vcemail,vc.vccontactno,vc.vcfax,vc.vccountryid,cn.countryname," + 
			" vc.vcstateid,s.statename,vc.vccityid,c.cityname" + 
			" FROM visitorcompanymaster AS vc, countrymaster AS cn, statemaster AS s, citymaster AS c" + 
			" WHERE vc.vccountryid = cn.countryid AND vc.vcstateid = s.stateid AND vc.vccityid = c.cityid " + 
			" AND vc.active=1 " +
			" AND (LOWER(vcname) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(vcemail) LIKE LOWER('%' :sSearch '%')" +
			" OR (vccontactno) LIKE LOWER('%' :sSearch '%')" +
			" OR LOWER(countryname) LIKE LOWER('%' :sSearch '%')" +
			" OR LOWER(statename) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(cityname) LIKE LOWER('%' :sSearch '%'))",  nativeQuery = true)
	public List<?> getVisitorCompanyByQuery(Pageable pageable, @Param("sSearch") String sSearch);
}
