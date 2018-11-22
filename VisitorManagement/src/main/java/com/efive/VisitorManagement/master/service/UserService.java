package com.efive.VisitorManagement.master.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.efive.VisitorManagement.entity.Usermaster;

public interface UserService {

	public boolean save(Usermaster usermaster);
	
	public long countUserdata();
	
	public List<?> getUserByQuery();

	public Usermaster findById(Long userid);
}
