package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Citymaster;
@Transactional
@Repository
public interface CityMasterRepository extends JpaRepository<Citymaster, Long> {

	@Query(value = "SELECT c.cityid,c.cityname,c.stdcode,c.pincode,c.countryid,c.stateid,c.active "
			+ " FROM citymaster AS c "
			+ " WHERE c.active=1 "
			+ " AND (LOWER(cityname)  like LOWER('%' :sSearch  '%') "
			+ " OR LOWER(stdcode)  like LOWER('%' :sSearch '%') "
			+ " OR LOWER(pincode)  like LOWER('%' :sSearch '%'))", nativeQuery = true)
	public List<?> getCityByQuery(Pageable pageable, @Param("sSearch") String sSearch);

}
