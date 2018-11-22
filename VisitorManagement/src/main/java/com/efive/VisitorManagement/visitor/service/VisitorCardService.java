package com.efive.VisitorManagement.visitor.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.efive.VisitorManagement.entity.Visitorcard;

@Service
public interface VisitorCardService {
	
	public Boolean save(Visitorcard visitorcard);
	
	public long countVisitorCarddata();

	public List<?> getVisitorCardByQuery(Pageable pageable, String sSearch);

	public Visitorcard findById(Long id);

}
