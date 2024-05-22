package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.dto.ApplicantDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.enums.ApplicationStatus;
import com.backend.hiretop.service.ApplicantService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<Applicant>> getApplicantById(@PathVariable Long id) {
        return ResponseEntity.ok(applicantService.getApplicantById(id));
    }

    @GetMapping
    public ResponseEntity<ResponseVO<Applicant>> getLoggedInApplicantDetails(
            @AuthenticationPrincipal Applicant applicant) {
        return ResponseEntity.ok(applicantService.getLoggedInApplicantDetails(applicant));
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Applicant>> updateApplicant(@AuthenticationPrincipal Applicant applicant,
            @RequestBody ApplicantDto request) {
        return ResponseEntity.ok(applicantService.updateApplicant(applicant, request));
    }

    @GetMapping("/applications/count/all")
    public ResponseEntity<ResponseVO<Number>> getUserTotalApplications(@AuthenticationPrincipal Applicant applicant) {
        return ResponseEntity.ok(applicantService.getUserTotalApplications(applicant));
    }

    @GetMapping("/applications")
    public ResponseEntity<ResponsePageableVO<Application>> getUserApplications(
        @AuthenticationPrincipal Applicant applicant,
        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
        @RequestParam(name = "sortBy", required = false, defaultValue = "createdAt") String sortBy,
        @RequestParam(name = "sortDirection", required = false, defaultValue = "DESC") String sortDirection,
        @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

    // Default dates if not provided
    if (startDate == null) {
        startDate = LocalDateTime.of(1900, 1, 1, 0, 0);
    }
    if (endDate == null) {
        endDate = LocalDateTime.now();
    }

    return ResponseEntity.ok(applicantService.getApplications(applicant, page, size, sortBy, sortDirection, startDate, endDate));
}

    @GetMapping("/applications/count")
    public ResponseEntity<ResponseVO<Number>> countUserAppliedJobStatus(@RequestParam String q,
            @AuthenticationPrincipal Applicant applicant) {
        return ResponseEntity.ok(applicantService.countUserAppliedJobStatus(q, applicant));
    }
}