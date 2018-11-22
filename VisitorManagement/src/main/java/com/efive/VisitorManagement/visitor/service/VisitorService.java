package com.efive.VisitorManagement.visitor.service;

import org.springframework.stereotype.Service;
import com.efive.VisitorManagement.entity.Visitor;

@Service
public interface VisitorService {

	public boolean save(Visitor visitor);

	public long countVisitordata();

	//public List<?> getVisitorByQuery(Pageable pageable, String sSearch);
	public Visitor findById(Long vid);

}
