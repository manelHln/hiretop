package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Education;
import com.backend.hiretop.domain.Experience;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.dto.ApplicantDto;
import com.backend.hiretop.dto.EducationDto;
import com.backend.hiretop.dto.ExperienceDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.enums.ApplicationStatus;
import com.backend.hiretop.service.ApplicantService;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // @PutMapping
    // public ResponseEntity<ResponseVO<Applicant>> updateApplicant(@AuthenticationPrincipal Applicant applicant,
    //         @RequestBody ApplicantDto request) {
    //     return ResponseEntity.ok(applicantService.updateApplicant(applicant, request));
    // }

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

        if (startDate == null) {
            startDate = LocalDateTime.of(1900, 1, 1, 0, 0);
        }
        if (endDate == null) {
            endDate = LocalDateTime.now();
        }

        return ResponseEntity
                .ok(applicantService.getApplications(applicant, page, size, sortBy, sortDirection, startDate, endDate));
    }

    @GetMapping("/applications/count")
    public ResponseEntity<ResponseVO<Number>> countUserAppliedJobStatus(@RequestParam String q,
            @AuthenticationPrincipal Applicant applicant) {
        return ResponseEntity.ok(applicantService.countUserAppliedJobStatus(q, applicant));
    }

    @PostMapping("/bookmark/{jobId}")
    public ResponseEntity<Void> bookmarkJob(@AuthenticationPrincipal Applicant applicant, @PathVariable Long jobId) {
        applicantService.bookmarkJob(applicant.getId(), jobId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{applicantId}/bookmark/{jobId}")
    public ResponseEntity<Void> removeBookmark(@PathVariable Long applicantId, @PathVariable Long jobId) {
        applicantService.removeBookmark(applicantId, jobId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bookmark")
    public ResponseEntity<ResponsePageableVO<Job>> getBookmarkedJobs(@AuthenticationPrincipal Applicant applicant,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(applicantService.getBookmarkedJobs(applicant.getId(), page, size));
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Applicant>> addResume(@AuthenticationPrincipal Applicant applicant, @RequestBody ApplicantDto detailsDto) {
        return ResponseEntity.ok(applicantService.updateDetails(applicant, detailsDto));
    }
    
    @PostMapping(value = "/add-picture", consumes = "multipart/form-data")
    public ResponseEntity<ResponseVO<String>> uploadLogo(@AuthenticationPrincipal Applicant applicant, @RequestBody MultipartFile file){
        return ResponseEntity.ok(applicantService.updateProfile(applicant, file));
    }

    @PostMapping("/education")
    public ResponseEntity<ResponseVO<Education>> addEducation(@AuthenticationPrincipal Applicant applicant, @RequestBody EducationDto educationDto){
        return ResponseEntity.ok(applicantService.addEducation(applicant, educationDto));
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<ResponseVO<Education>> updateEducation(@AuthenticationPrincipal Applicant applicant, @PathVariable Long id, @RequestBody EducationDto educationDto){
        return ResponseEntity.ok(applicantService.updateEducation(id, educationDto));
    }

    @DeleteMapping("/education/{id}")
    public ResponseEntity<ResponseVO<String>> deleteEducation(@PathVariable Long id){
        return ResponseEntity.ok(applicantService.deleteEducation(id));
    }

    @PostMapping("/experience")
    public ResponseEntity<ResponseVO<Experience>> addExperience(@AuthenticationPrincipal Applicant applicant, @RequestBody ExperienceDto experienceDto){
        return ResponseEntity.ok(applicantService.addExperience(applicant, experienceDto));
    }

    @PutMapping("/experience/{id}")
    public ResponseEntity<ResponseVO<Experience>> updateExperience(@AuthenticationPrincipal Applicant applicant, @PathVariable Long id, @RequestBody ExperienceDto experienceDto){
        return ResponseEntity.ok(applicantService.updateExperience(id, experienceDto));
    }

    @DeleteMapping("/experience/{id}")
    public ResponseEntity<ResponseVO<String>> deleteExperience(@PathVariable Long id){
        return ResponseEntity.ok(applicantService.deleteExperience(id));
    }

    @PostMapping("/skill")
    public ResponseEntity<ResponseVO<Set<Skill>>> addSkills(@AuthenticationPrincipal Applicant applicant, @RequestBody String[] skills){
        return ResponseEntity.ok(applicantService.addSkills(applicant, skills));
    }

    @DeleteMapping("/skill/{id}")
    public ResponseEntity<ResponseVO<String>> removeSkill(@AuthenticationPrincipal Applicant applicant, @PathVariable Long id){
        return ResponseEntity.ok(applicantService.removeskill(applicant, id));
    }
}