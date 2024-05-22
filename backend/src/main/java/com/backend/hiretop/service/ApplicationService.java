package com.backend.hiretop.service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.dto.ApplicationDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.enums.ApplicationStatus;
import com.backend.hiretop.repository.ApplicationRepository;
import com.backend.hiretop.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application updateApplication(Long id, Application applicationDetails) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setCoverLetter(applicationDetails.getCoverLetter());
        application.setPortfolio(applicationDetails.getPortfolio());
        application.setAdditionnalInfo(applicationDetails.getAdditionnalInfo());
        application.setResume(applicationDetails.getResume());
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public ResponseVO<Number> countApplicantApplications(Applicant applicant) {
        long totalApplications = applicationRepository.countByApplicant(applicant);
        return new ResponseVOBuilder<Number>().success().addData(totalApplications).build();
    }

    public ResponsePageableVO<Application> getUserApplications(Applicant applicant, int page, int size, String sortBy,
            String sortDirection, LocalDateTime startDate, LocalDateTime endDate) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Application> applications = applicationRepository.findByApplicantAndCreatedAtBetween(applicant, startDate,
                endDate, pageable);
        if (applications.isEmpty()) {
            return new ResponsePageableVO<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        return new ResponsePageableVO<>(applications.getContent(), applications.getNumber(), applications.getSize(),
                applications.getTotalElements(), applications.getTotalPages(), applications.isLast());
    }

    public Number countUserAppliedJobStatus(ApplicationStatus q, Applicant applicant) {
        return applicationRepository.countByApplicantAndStatus(applicant, q);
    }

    public ResponseVO<String> apply(Applicant applicant, long jobId, ApplicationDto request) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("There was an error retrieving job"));
        String resume = "";
        if(!request.getResume().isEmpty()){
            resume = fileStorageService.store(request.getResume());
        }

        Application application = Application.builder().additionnalInfo(request.getAdditionnalInfo())
                .applicant(applicant).coverLetter(request.getCoverLetter())
                .portfolio(request.getPortfolio()).resume(resume).status(ApplicationStatus.APPLIED).build();
        applicationRepository.save(application);
        job.getApplications().add(application);
        jobRepository.save(job);

        return new ResponseVOBuilder<String>().success().build();
    }
}