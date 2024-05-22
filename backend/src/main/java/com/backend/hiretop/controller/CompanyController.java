package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Company;
import com.backend.hiretop.dto.CompanyDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.service.CompanyService;
import com.backend.hiretop.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<Company>> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping
    public ResponseEntity<ResponsePageableVO<Company>> getAllCompanies(
        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(companyService.getAllCompanies(page, size));
    }

    @GetMapping("/jobs/count")
    public ResponseEntity<ResponseVO<Number>> countJobByStatus(@AuthenticationPrincipal Company company, @RequestParam String status){
        return ResponseEntity.ok(jobService.countJobByStatus(company, status));
    }

    @GetMapping("/jobs/applications/count")
    public ResponseEntity<ResponseVO<Number>> countJobApplied(@AuthenticationPrincipal Company company, @RequestParam String status){
        return ResponseEntity.ok(jobService.countJobApplied(company, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        try {
            Company updatedCompany = companyService.updateCompany(id, companyDetails);
            return ResponseEntity.ok(updatedCompany);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
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
}