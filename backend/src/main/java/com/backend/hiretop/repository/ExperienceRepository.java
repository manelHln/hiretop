package com.backend.hiretop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.hiretop.domain.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    
}
