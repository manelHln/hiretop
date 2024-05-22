package com.backend.hiretop.service;

import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Education;
import com.backend.hiretop.dto.EducationDto;
import com.backend.hiretop.repository.EducationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;

    public Education createEducation(EducationDto request){
        Education education = Education.builder().build();
        return educationRepository.save(education);
    }
}
