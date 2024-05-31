package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.dto.CompanyDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.service.ApplicationService;
import com.backend.hiretop.service.CompanyService;
import com.backend.hiretop.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<Company>> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping
    public ResponseEntity<ResponsePageableVO<Company>> getAllCompanies(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(companyService.getAllCompanies(page, size));
    }

    @GetMapping("/applications")
    public ResponseEntity<ResponsePageableVO<Application>> getCompanyApplicationsList(
            @AuthenticationPrincipal Company company,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        return ResponseEntity.ok(applicationService.getCompanyApplications(company, page, size));
    }

    @GetMapping("/jobs")
    public ResponseEntity<ResponsePageableVO<Job>> getCompanyJobResponseEntity(@AuthenticationPrincipal Company company, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(jobService.getCompanyJobs(company, page, size));
    }
    

    @GetMapping("/jobs/count")
    public ResponseEntity<ResponseVO<Number>> countJobByStatus(@AuthenticationPrincipal Company company,
            @RequestParam String status) {
        return ResponseEntity.ok(jobService.countJobByStatus(company, status));
    }

    @GetMapping("/jobs/applications/count")
    public ResponseEntity<ResponseVO<Number>> countJobApplied(@AuthenticationPrincipal Company company,
            @RequestParam String status) {
        return ResponseEntity.ok(jobService.countJobApplied(company, status));
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Company>> addResume(@AuthenticationPrincipal Company applicant,
            @RequestBody CompanyDto detailsDto) {
        return ResponseEntity.ok(companyService.updateCompany(applicant.getId(), detailsDto));
    }

    @PostMapping(value = "/add-logo", consumes = "multipart/form-data")
    public ResponseEntity<ResponseVO<String>> uploadLogo(@AuthenticationPrincipal Company company,
            @RequestBody MultipartFile file) {
        return ResponseEntity.ok(companyService.updateLogo(company, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/analytics/jobs/total")
    public ResponseEntity<ResponseVO<Long>> getTotalJobsPosted(@AuthenticationPrincipal Company company) {
        return ResponseEntity.ok(companyService.getTotalJobsPostedByCompany(company.getId()));
    }

    @GetMapping("/analytics/jobs/average")
    public ResponseEntity<ResponseVO<Double>> getAverageJobsPosted() {
        return ResponseEntity.ok(companyService.getAverageJobsPostedPerCompany());
    }

    @GetMapping("/analytics/jobs/location")
    public ResponseEntity<ResponseVO<Number>> getJobTrendsByLocation(@RequestParam String location) {
        return ResponseEntity.ok(companyService.getJobTrendsByLocation(location));
    }

    @GetMapping("/analytics/jobs/title")
    public ResponseEntity<ResponseVO<Number>> getJobTrendsByJobTitle(@RequestParam String jobTitle) {
        return ResponseEntity.ok(companyService.getJobTrendsByJobTitle(jobTitle));
    }
}