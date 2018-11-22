package com.efive.VisitorManagement.visitor.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.Visitorcard;
@Transactional
@Repository
public interface VisitorCardRepository extends JpaRepository<Visitorcard,Long> {
	

}
