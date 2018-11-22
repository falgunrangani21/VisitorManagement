package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Statemaster;

@Transactional
@Repository

public interface StateMasterRepository extends JpaRepository<Statemaster, Long>{

	@Query(value="SELECT s.stateid,s.statename,s.countryid,s.active "
				+ " FROM statemaster AS s "
				+ " WHERE s.active = 1 "
				+ " AND (LOWER(statename)  like LOWER('%' :sSearch  '%'))", nativeQuery = true)
	public List<?> getStateByQuery(Pageable pageable,@Param("sSearch")String sSearch);
	
}
