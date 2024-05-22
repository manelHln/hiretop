package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.dto.ApplicationDto;
import com.backend.hiretop.dto.JobDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.service.ApplicationService;
import com.backend.hiretop.service.JobService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ResponseVO<Job>> createJob(@AuthenticationPrincipal Company company,
            @RequestBody JobDto job) {
        return ResponseEntity.ok(jobService.createJob(company, job));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<Job>> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<ResponsePageableVO<Job>> getAllJobs(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(jobService.getAllJobs(page, size));
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<ResponsePageableVO<Job>> getJobByCategoy(@PathVariable String cat, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(jobService.getJobByCategoy(cat, page, size));
    }

    @GetMapping("/apply")
    public ResponseEntity<ResponseVO<String>> apply(@AuthenticationPrincipal Applicant applicant, @RequestParam long jobId, @ModelAttribute @Valid ApplicationDto request){
        return ResponseEntity.ok(applicationService.apply(applicant, jobId, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        try {
            Job updatedJob = jobService.updateJob(id, jobDetails);
            return ResponseEntity.ok(updatedJob);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}