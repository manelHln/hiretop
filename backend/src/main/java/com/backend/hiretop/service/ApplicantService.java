package com.backend.hiretop.service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.dto.ApplicantDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.enums.ApplicationStatus;
import com.backend.hiretop.enums.Gender;
import com.backend.hiretop.repository.ApplicantRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FileStorageService fileStorageService;

    public ResponseVO<Applicant> getLoggedInApplicantDetails(Applicant applicantDetails) {
        Applicant applicant = applicantRepository.findByEmail(applicantDetails.getEmail()).get();
        return new ResponseVOBuilder<Applicant>().success().addData(applicant).build();
    }

    public ResponseVO<Applicant> getApplicantById(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + id));
        return new ResponseVOBuilder<Applicant>().success().addData(applicant).build();
    }

    public ResponseVO<Applicant> updateApplicant(Applicant applicant, ApplicantDto applicantDetails) {
        String profilePicture = "";

        if (applicantDetails.getProfilePicture() != null && !applicantDetails.getProfilePicture().isEmpty()) {
            profilePicture = fileStorageService.store(applicantDetails.getProfilePicture());
        }

        String resume = "";

        if (applicantDetails.getResume() != null && !applicantDetails.getResume().isEmpty()) {
            resume = fileStorageService.store(applicantDetails.getResume());
        }

        applicant.setFirstname(applicantDetails.getFirstname());
        applicant.setLastname(applicantDetails.getLastname());
        applicant.setPassword(applicantDetails.getPassword());
        applicant.setGender(Gender.valueOf(applicantDetails.getGender().toUpperCase()));
        applicant.setAbout(applicantDetails.getAbout());
        applicant.setEmail(applicantDetails.getEmail());
        applicant.setProfilePicture(profilePicture);
        applicant.setResume(resume);
        applicant.setBirthDate(applicantDetails.getBirthDate());
        // applicant.setContacts(applicantDetails.getContacts());
        // applicant.setOtherEmails(applicantDetails.getOtherEmails());
        applicant.setLocations(applicantDetails.getLocations());
        applicant.setLanguages(applicantDetails.getLanguages());
        applicant.setExperiences(applicantDetails.getExperiences());
        applicant.setEducations(applicantDetails.getEducations());
        applicant.setApplications(applicantDetails.getApplications());

        return new ResponseVOBuilder<Applicant>().success().addData(applicantRepository.save(applicant)).build();
    }

    public ResponseVO<Number> getUserTotalApplications(Applicant applicant) {
        return applicationService.countApplicantApplications(applicant);
    }

    public ResponsePageableVO<Application> getApplications(Applicant applicant, int page, int size, String sortBy, String sortDirection, LocalDateTime startDate, LocalDateTime endDate){
        return applicationService.getUserApplications(applicant, page, size, sortBy, sortDirection, startDate, endDate);
    }

    public ResponseVO<Number> countUserAppliedJobStatus(String q, Applicant applicant) {
        return new ResponseVOBuilder<Number>().success().addData(applicationService.countUserAppliedJobStatus(ApplicationStatus.valueOf(q.toUpperCase()), applicant)).build();
    }

}