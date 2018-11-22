package com.efive.VisitorManagement.visitor.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.efive.VisitorManagement.entity.Visitor;
@Transactional
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
	

}
