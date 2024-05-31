package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/")
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        return ResponseEntity.ok(applicationService.createApplication(application));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application applicationDetails) {
        try {
            Application updatedApplication = applicationService.updateApplication(id, applicationDetails);
            return ResponseEntity.ok(updatedApplication);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteApplication(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/schedule/{id}")
    public ResponseEntity<Application> scheduleInterview(@PathVariable Long id, @RequestBody Date date, @AuthenticationPrincipal Company company) {
        return ResponseEntity.ok(applicationService.scheduleInterview(id, date, company));
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<Application> setApplicationStatuResponseEntity(@RequestBody String status, @PathVariable Long id, @AuthenticationPrincipal Company company) {        
        return ResponseEntity.ok(applicationService.updateApplicationStatus(id, status, company));
    }    
}