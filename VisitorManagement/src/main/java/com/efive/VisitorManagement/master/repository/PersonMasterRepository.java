package com.efive.VisitorManagement.master.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efive.VisitorManagement.entity.ContactPersonMaster;
@Transactional
@Repository
public interface PersonMasterRepository extends JpaRepository<ContactPersonMaster, Long>{

}
