package com.efive.VisitorManagement.master.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

	public List<?> findUserByusernameAndpassword(String username, String password);
	
}
