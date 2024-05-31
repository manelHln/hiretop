package com.backend.hiretop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Experience;
import com.backend.hiretop.dto.EducationDto;
import com.backend.hiretop.dto.ExperienceDto;
import com.backend.hiretop.enums.JobType;
import com.backend.hiretop.repository.ExperienceRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public Experience createExperience(ExperienceDto experienceDto) {
        Experience experience = Experience.builder()
                .jobType(JobType.valueOf(experienceDto.getJobType().toUpperCase()))
                .role(experienceDto.getRole())
                .company(experienceDto.getCompany())
                .description(experienceDto.getDescription())
                .from(experienceDto.getFromDate())
                .to(experienceDto.getToDate())
                .location(experienceDto.getLocation())
                .description(experienceDto.getDescription())
                .build();
        return experienceRepository.save(experience);
    }

    public String deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        experienceRepository.delete(experience);
        return "Experience deleted successfully!!";
    }

    public Experience updateExperience(Long id, ExperienceDto experienceDto) {
        Experience experience = experienceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        experience.setCompany(experienceDto.getCompany());
        experience.setDescription(experienceDto.getDescription());
        experience.setFrom(experienceDto.getFromDate());
        experience.setTo(experienceDto.getToDate());
        experience.setJobType(JobType.valueOf(experienceDto.getJobType().toUpperCase()));
        experience.setLocation(experienceDto.getLocation());
        experience.setRole(experience.getRole());
        return experienceRepository.save(experience);
    }
}
