package com.efive.VisitorManagement.master.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Usermaster;

@Repository
@Transactional
public interface LoginRepository extends CrudRepository<Usermaster, Long>{

	@Query(value="select u.userid,u.loginname,u.usertype,u.roleaccesstype,u.gender,u.email,u.contactnumber,u.countryid,u.stateid,u.cityid,u.passwordupdateon,"
			+ "u.isfirst,u.last_login,u.active from Usermaster u where loginname = :loginname and password = :password and active=1 ",nativeQuery=true)
	public List<?> findByusernameAndPassword(@Param("loginname") String username, @Param("password") String password);

}
