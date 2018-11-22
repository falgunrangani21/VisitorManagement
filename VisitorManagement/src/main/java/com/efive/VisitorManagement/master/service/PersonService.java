package com.efive.VisitorManagement.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.efive.VisitorManagement.entity.ContactPersonMaster;

public interface PersonService {
	
	public boolean save(ContactPersonMaster contactPersonMaster);
	public ContactPersonMaster findById(Long companyid);
	public List<?> getPersonByQuery(Pageable pageable,String sSearch);
	public long countPersondata();

}
