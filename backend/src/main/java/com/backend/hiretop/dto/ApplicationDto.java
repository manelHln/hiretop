package com.backend.hiretop.dto;


import org.springframework.web.multipart.MultipartFile;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Job;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ApplicationDto {
    private String coverLetter;

    private String additionnalInfo;

    private Job job;

    private Applicant applicant;
}
