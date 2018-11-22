package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.ContactPersonMaster;
import com.efive.VisitorManagement.master.repository.PersonMasterRepository;

@Service("PersonService")
public class PersonServiceImpli implements PersonService {
	@Autowired
	PersonMasterRepository personmasterRepository;

	@Override
	public boolean save(ContactPersonMaster contactPersonMaster) {

		personmasterRepository.save(contactPersonMaster);
		return true;
	}

	@Override
	public ContactPersonMaster findById(Long companyid) {

		return personmasterRepository.getOne(companyid);
	}

	@Override
	public List<?> getPersonByQuery(Pageable pageable, String sSearch) {
		
		return null;
	}

	@Override
	public long countPersondata() {
		
		return 0;
	}

}
