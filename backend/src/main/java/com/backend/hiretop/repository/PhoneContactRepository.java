package com.backend.hiretop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.hiretop.domain.PhoneContact;

@Repository
public interface PhoneContactRepository extends JpaRepository<PhoneContact, Long> {
    
}
