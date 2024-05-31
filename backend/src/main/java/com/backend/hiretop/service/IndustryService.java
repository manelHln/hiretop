package com.backend.hiretop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Industry;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.repository.IndustryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndustryService {
    private final IndustryRepository industryRepository;

    public ResponseVO<List<Industry>> getAllIndustries() {
        return new ResponseVOBuilder<List<Industry>>().success().addData(industryRepository.findAll()).build();
    }

    public Industry create(String industryName, Company company) {
        Industry industry = industryRepository.findByName(industryName)
                .orElseGet(() -> {
                    Industry newIndustry = new Industry();
                    newIndustry.setName(industryName);
                    industryRepository.save(newIndustry);
                    return newIndustry;
                });
        if (!industry.getCompanies().contains(company)) {
            industry.getCompanies().add(company);
            company.getIndustries().add(industry);
        }
        return industryRepository.save(industry);
    }
}
