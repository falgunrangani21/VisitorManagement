package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.master.repository.LoginRepository; 

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginrepository;

	@Override
	public List<?> findUserByusernameAndpassword(String username, String password) {

		return loginrepository.findByusernameAndPassword(username, password);
	}

}
