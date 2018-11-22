package com.efive.VisitorManagement.visitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.efive.VisitorManagement.entity.Visitorcard;
import com.efive.VisitorManagement.visitor.repository.VisitorCardRepository;

@Service("VisitorCardService")
public class VisitorCardServiceImpl implements VisitorCardService {

	@Autowired
	private VisitorCardRepository vcardrepository;

	@Override
	public Boolean save(Visitorcard visitorcard) {
		vcardrepository.save(visitorcard);
		return true;
	}

	@Override
	public long countVisitorCarddata() {

		return vcardrepository.count();
	}

	@Override
	public List<?> getVisitorCardByQuery(Pageable pageable, String sSearch) {

		return null;
	}

	@Override
	public Visitorcard findById(Long id) {

		return vcardrepository.getOne(id);
	}

}
