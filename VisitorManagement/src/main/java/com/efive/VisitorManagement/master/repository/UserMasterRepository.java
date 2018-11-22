package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Usermaster;

@Transactional
@Repository
public interface UserMasterRepository extends JpaRepository<Usermaster, Long> {

	@Query(value="SELECT u.userid,u.profileurl,u.username,u.email,u.contactnumber,u.gender, (CASE " + 
			" WHEN u.gender='m' THEN 'Male' ELSE 'Female' END) as genders,u.countryid,cn.countryname,u.stateid," +
			" s.statename,u.cityid,c.cityname,u.loginname,u.password,u.usertype,u.roleaccesstype,cn.isdcode," + 
			" (CASE WHEN u.usertype='U' THEN 'User'" + 
			" WHEN u.usertype='A' THEN 'Admin'" + 
			" WHEN u.usertype='Z' THEN 'System Admin'" + 
			" WHEN u.usertype='S' THEN 'Super Admin' END) AS users" +
			" FROM usermaster AS u, countrymaster AS cn, statemaster AS s, citymaster AS c" + 
			" WHERE u.countryid = cn.countryid AND u.stateid = s.stateid AND u.cityid = c.cityid AND u.active=1 " , nativeQuery = true)
			/*" AND (LOWER(username) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(email) LIKE LOWER('%' :sSearch '%')" + 
			" OR (contactnumber) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(gender) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(countryname) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(statename) LIKE LOWER('%' :sSearch '%')" + 
			" OR LOWER(cityname) LIKE LOWER('%' :sSearch '%'))"*/
	public List<?> getUserByQuery();
	
}
