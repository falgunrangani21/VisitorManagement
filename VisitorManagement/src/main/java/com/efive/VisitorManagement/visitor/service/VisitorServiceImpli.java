package com.efive.VisitorManagement.visitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Visitor;
import com.efive.VisitorManagement.visitor.repository.VisitorRepository;

@Service("VisitorService")
public class VisitorServiceImpli implements VisitorService{

	@Autowired
	private VisitorRepository vrepository;
	@Override
	public boolean save(Visitor visitor) {
		 vrepository.save(visitor);
		 return true;
	}

	@Override
	public long countVisitordata() {
	
		return vrepository.count();
	}

	/*@Override
	public List<?> getVisitorByQuery(Pageable pageable, String sSearch) {
	
		return vrepository.getVisitorByQuery(pageable, sSearch);
	}*/

	@Override
	public Visitor findById(Long vid) {
	
		return vrepository.getOne(vid);
	}
}