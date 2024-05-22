package com.backend.hiretop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hiretop.domain.Industry;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    
}
