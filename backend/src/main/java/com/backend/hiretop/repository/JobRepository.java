package com.backend.hiretop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.enums.JobStatus;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    int countByCompanyAndStatus(Company company, JobStatus status);
    List<Job> findByCompanyAndStatus(Company company, JobStatus status);
    
    @Query("SELECT j FROM Job j JOIN j.categories c WHERE c.name = :categoryName")
    Page<Job> findByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    Long countByCompanyId(Long companyId);

    int countByLocation(String location);

    int countByJobTitle(String jobTitle);
    
    Page<Job> findByCompany(Company company, Pageable pageable);
}
