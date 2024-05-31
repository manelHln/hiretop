package com.backend.hiretop.service;

import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Industry;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.dto.CompanyDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.repository.CompanyRepository;
import com.backend.hiretop.repository.JobRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private IndustryService industryService;

    public ResponseVO<Company> getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There was an error retrieving commpany"));
        return new ResponseVOBuilder<Company>().success().addData(company).build();
    }

    public ResponsePageableVO<Company> getAllCompanies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Company> companies = companyRepository.findAll(pageable);
        if (companies.isEmpty()) {
            return new ResponsePageableVO<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        return new ResponsePageableVO<>(companies.getContent(), companies.getNumber(), companies.getSize(),
                companies.getTotalElements(), companies.getTotalPages(), companies.isLast());
    }

    public ResponseVO<Company> updateCompany(Long id, CompanyDto companyDetails) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));

        if (companyDetails.getName() != null) {
            company.setName(companyDetails.getName());

        }

        if (companyDetails.getHistory() != null) {
            company.setHistory(companyDetails.getHistory());
        }

        if (companyDetails.getCompanyWebsite() != null) {
            company.setCompanyWebsite(companyDetails.getCompanyWebsite());
        }

        if (companyDetails.getFounded() != null) {
            company.setFounded(companyDetails.getFounded());
        }

        for(String industry : companyDetails.getIndustries()){
            industryService.create(industry, company);
        }

        return new ResponseVOBuilder<Company>().success().addData(companyRepository.save(company)).build();
    }

    

    public ResponseVO<String> updateLogo(Company company, MultipartFile file) {
        company.setLogo(fileStorageService.store(file));
        companyRepository.save(company);
        return new ResponseVOBuilder<String>().success().addData("Logo successfully updated!!").build();
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    public Company getCompanyWithJobs(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        company.getJobs().size();
        return company;
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Company not found"));
    }

    public ResponseVO<Double> getAverageJobsPostedPerCompany() {
        List<Long> jobCounts = companyRepository.findAll().stream()
                .map(company -> jobRepository.countByCompanyId(company.getId()))
                .collect(Collectors.toList());
        OptionalDouble average = jobCounts.stream().mapToLong(val -> val).average();
        return new ResponseVOBuilder<Double>().success().addData(average.orElse(0.0)).build();
    }

    public ResponseVO<Number> getJobTrendsByLocation(String location) {
        return new ResponseVOBuilder<Number>().success().addData(jobRepository.countByLocation(location)).build();
    }

    public ResponseVO<Number> getJobTrendsByJobTitle(String jobTitle) {
        return new ResponseVOBuilder<Number>().success().addData(jobRepository.countByJobTitle(jobTitle)).build();
    }

    public ResponseVO<Long> getTotalJobsPostedByCompany(Long companyId) {
        Long count = jobRepository.countByCompanyId(companyId);
        return new ResponseVOBuilder<Long>().success().addData(count).build();
    }
}