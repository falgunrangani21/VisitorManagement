package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Statemaster;
import com.efive.VisitorManagement.master.repository.StateMasterRepository;

@Service("StateService")
public class StateServiceImpli implements StateService {

	@Autowired
	private StateMasterRepository staterepository;
	
	@Override
	public boolean save(Statemaster statemaster) {
		staterepository.save(statemaster);
		return true;
	}

	@Override
	public Long countStatedata() {
		
		return staterepository.count();
	}

	@Override
	public List<?> getStateByQuery(Pageable pageable, String sSearch) {
		
		return staterepository.getStateByQuery(pageable, sSearch);
	}

	@Override
	public Statemaster findById(Long stateid) {
		return staterepository.getOne(stateid);
	}
}