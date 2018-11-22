package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Usermaster;
import com.efive.VisitorManagement.master.repository.UserMasterRepository;

@Service("UserService")
public class UserServiceImpli implements UserService {

	@Autowired
	private UserMasterRepository userrepository;
	
	@Override
	public boolean save(Usermaster usermaster) {
		userrepository.save(usermaster);
		return true;
	}

	@Override
	public long countUserdata() {
	
		return userrepository.count();
	}

	@Override
	public Usermaster findById(Long userid) {
	
		return userrepository.getOne(userid);
	}

	@Override
	public List<?> getUserByQuery() {
		return userrepository.getUserByQuery();
	}
	

}
