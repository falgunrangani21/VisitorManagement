package com.efive.VisitorManagement.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efive.VisitorManagement.entity.Locationmaster;

@Service
public interface LocationService {

	public boolean save(Locationmaster locationmaster);

	public Locationmaster findById(Long locationid);

	public List<?> getBranchByQuery(Pageable pageable, String sSearch);

	public long countBranchdata();
}
