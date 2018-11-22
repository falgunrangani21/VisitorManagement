package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Countrymaster;

@Transactional
@Repository
public interface CountryMasterRepository extends JpaRepository<Countrymaster, Long>{

	@Query(value="SELECT c.countryid,c.countryname,c.isdcode,c.active "
			+ " FROM countrymaster AS c "
			+ " WHERE c.active = 1 "
			+ " AND (LOWER(countryname)  like LOWER('%' :sSearch  '%') "
			+ " OR LOWER(isdcode)  like LOWER('%' :sSearch '%'))", nativeQuery = true)
	public List<?> getCountryByQuery(Pageable pageable,@Param("sSearch")String sSearch);
}
