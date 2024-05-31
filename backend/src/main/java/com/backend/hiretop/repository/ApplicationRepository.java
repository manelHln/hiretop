package com.backend.hiretop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.enums.ApplicationStatus;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByApplicant(Applicant applicant, Pageable pageable);
    long countByApplicant(Applicant applicant);
    long countByApplicantAndStatus(Applicant applicant, ApplicationStatus status);
    Page<Application> findByApplicantAndCreatedAtBetween(Applicant applicant, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT a FROM Application a WHERE a.job.company = :company")
    Page<Application> findByJob_Company(@Param("company") Company company, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Application a WHERE a.status = :status AND a.job.company.id = :companyId")
    long countByStatusAndCompanyId(@Param("status") ApplicationStatus status, @Param("companyId") Long companyId);
}
