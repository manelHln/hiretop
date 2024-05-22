package com.backend.hiretop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.hiretop.domain.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    
}
