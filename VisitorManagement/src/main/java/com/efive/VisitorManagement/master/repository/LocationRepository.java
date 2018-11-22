package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.efive.VisitorManagement.entity.Locationmaster;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Locationmaster, Long> {

	// AND l.locationid=1 
	@Query(value="SELECT l.locationid,l.branchname,com.cname,l.companyid,l.email,l.contactno,l.fax,l.countryid,l.stateid,l.cityid, "
			+ " l.address,c.countryname,s.statename,ci.cityname,l.active "
			+ " FROM locationmaster AS l ,companymaster	AS com,countrymaster AS c,statemaster AS s,citymaster AS ci "
			+ " WHERE l.active=1 AND l.countryid=c.countryid AND l.stateid=s.stateid "
			+ " AND l.cityid=ci.cityid AND l.companyid=com.companyid " 
			+ " AND (LOWER(branchname)  like LOWER('%' :sSearch  '%') or "
			+ " LOWER(email)  like LOWER('%' :sSearch '%') or "
			+ " LOWER(cname)  like LOWER('%' :sSearch '%') or "
			+ " LOWER(contactno)  like LOWER('%' :sSearch '%') or "
			+ " LOWER(address)  like LOWER('%' :sSearch '%') or "
			+ " LOWER(countryname)  like LOWER('%' :sSearch '%') or "
			+ " LOWER(statename)  like LOWER('%' :sSearch '%') or "
			+ " LOWER(cityname)  like LOWER('%' :sSearch '%')) " , nativeQuery = true)
	public List<?> getBranchByQuery(Pageable pageable,@Param("sSearch")String sSearch);
}
