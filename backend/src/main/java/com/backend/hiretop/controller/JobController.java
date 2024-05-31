package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.dto.ApplicationDto;
import com.backend.hiretop.dto.JobDto;
import com.backend.hiretop.dto.JobFilterDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.service.ApplicationService;
import com.backend.hiretop.service.JobService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<ResponsePageableVO<Job>> getJobByCategoy(@PathVariable String cat,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(jobService.getJobByCategoy(cat, page, size));
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<ResponseVO<String>> apply(@AuthenticationPrincipal Applicant applicant,
            @PathVariable long jobId, @RequestBody ApplicationDto request) {
        return ResponseEntity.ok(applicationService.apply(applicant, jobId, request));
    }

    @GetMapping("/filter")
    public ResponseEntity<ResponsePageableVO<Job>> filterJobs(
            @RequestParam(name = "categories", required = false) List<String> categories,
            @RequestParam(name = "skills", required = false) List<String> skills,
            @RequestParam(name = "jobTitle", required = false) String jobTitle,
            @RequestParam(name = "salaryFrom", required = false) Double salaryFrom,
            @RequestParam(name = "salaryTo", required = false) Double salaryTo,
            @RequestParam(name = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate,
            @RequestParam(name = "jobLevel", required = false) String jobLevel,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        JobFilterDto filterDto = new JobFilterDto();
        filterDto.setCategories(categories);
        filterDto.setSkills(skills);
        filterDto.setJobTitle(jobTitle);
        filterDto.setSalaryFrom(salaryFrom);
        filterDto.setSalaryTo(salaryTo);
        filterDto.setCreatedDate(createdDate);
        filterDto.setJobLevel(jobLevel);

        return ResponseEntity.ok(jobService.filterJobs(filterDto, page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVO<Job>> updateJob(@PathVariable Long id, @RequestBody JobDto jobDetails) {
        return ResponseEntity.ok(jobService.updateJob(id, jobDetails));
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