package com.backend.hiretop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hiretop.domain.Industry;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    Optional<Industry> findByName(String industryName);
    
}
