package com.backend.hiretop.service;

import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Education;
import com.backend.hiretop.dto.EducationDto;
import com.backend.hiretop.repository.EducationRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;

    public Education createEducation(EducationDto educationDto) {
        Education education = Education.builder()
                .university(educationDto.getUniversity())
                .diploma(educationDto.getDiploma())
                .from(educationDto.getFromDate())
                .to(educationDto.getToDate())
                .description(educationDto.getDescription())
                .build();
        return educationRepository.save(education);
    }

    public String deleteEducation(Long id) {
        Education education = educationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        educationRepository.delete(education);
        return "Successfully delete entity!!";
    }

    public Education updateEducation(Long id, EducationDto educationDto) {
        Education education = educationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        education.setDescription(educationDto.getDescription());
        education.setDiploma(educationDto.getDiploma());
        education.setFrom(educationDto.getFromDate());
        education.setTo(educationDto.getToDate());
        education.setUniversity(educationDto.getUniversity());
        return educationRepository.save(education);
    }
}
