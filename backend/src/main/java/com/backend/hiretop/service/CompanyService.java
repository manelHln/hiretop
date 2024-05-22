package com.backend.hiretop.service;

import com.backend.hiretop.domain.Company;
import com.backend.hiretop.dto.CompanyDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseVO<Company> getCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(()-> new RuntimeException("There was an error retrieving commpany"));
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

    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        company.setName(companyDetails.getName());
        company.setEmail(companyDetails.getEmail());
        company.setPassword(companyDetails.getPassword());
        company.setEnabled(companyDetails.isEnabled());
        company.setLogo(companyDetails.getLogo());
        company.setHistory(companyDetails.getHistory());
        company.setCompanyWebsite(companyDetails.getCompanyWebsite());
        company.setSize(companyDetails.getSize());
        company.setFounded(companyDetails.getFounded());
        // company.setOtherEmails(companyDetails.getOtherEmails());
        company.setJobs(companyDetails.getJobs());
        company.setSkills(companyDetails.getSkills());
        company.setAddresses(companyDetails.getAddresses());
        // company.setContacts(companyDetails.getContacts());
        company.setEmails(companyDetails.getEmails());
        company.setIndustries(companyDetails.getIndustries());
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    public Company getCompanyWithJobs(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        company.getJobs().size();
        return company;
    }
}