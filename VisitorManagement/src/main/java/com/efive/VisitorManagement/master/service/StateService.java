package com.efive.VisitorManagement.master.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Statemaster;

@Service
public interface StateService {
	
	public boolean save(Statemaster statemaster);
	public Statemaster findById(Long stateid);
	public Long countStatedata();	
	public List<?> getStateByQuery(Pageable pageable, String sSearch);

}
