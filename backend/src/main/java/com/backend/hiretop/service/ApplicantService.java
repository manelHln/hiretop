package com.backend.hiretop.service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.domain.Education;
import com.backend.hiretop.domain.Experience;
import com.backend.hiretop.dto.ApplicantDto;
import com.backend.hiretop.dto.EducationDto;
import com.backend.hiretop.dto.ExperienceDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.enums.ApplicationStatus;
import com.backend.hiretop.enums.Gender;
import com.backend.hiretop.enums.JobType;
import com.backend.hiretop.repository.ApplicantRepository;
import com.backend.hiretop.repository.JobRepository;
import com.backend.hiretop.repository.SkillRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    public ResponseVO<Applicant> getLoggedInApplicantDetails(Applicant applicantDetails) {
        Applicant applicant = applicantRepository.findByEmail(applicantDetails.getEmail()).get();
        return new ResponseVOBuilder<Applicant>().success().addData(applicant).build();
    }

    public ResponseVO<Applicant> getApplicantById(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + id));
        return new ResponseVOBuilder<Applicant>().success().addData(applicant).build();
    }

    public Applicant findById(Long id) {
        return applicantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Applicant not found"));
    }

    // public ResponseVO<Applicant> updateApplicant(Applicant applicant,
    // ApplicantDto applicantDetails) {
    // String profilePicture = "";

    // if (applicantDetails.getProfilePicture() != null &&
    // !applicantDetails.getProfilePicture().isEmpty()) {
    // profilePicture =
    // fileStorageService.store(applicantDetails.getProfilePicture());
    // }

    // String resume = "";

    // if (applicantDetails.getResume() != null &&
    // !applicantDetails.getResume().isEmpty()) {
    // resume = fileStorageService.store(applicantDetails.getResume());
    // }

    // applicant.setFirstname(applicantDetails.getFirstname());
    // applicant.setLastname(applicantDetails.getLastname());
    // applicant.setPassword(applicantDetails.getPassword());
    // applicant.setGender(Gender.valueOf(applicantDetails.getGender().toUpperCase()));
    // applicant.setAbout(applicantDetails.getAbout());
    // applicant.setEmail(applicantDetails.getEmail());
    // applicant.setProfilePicture(profilePicture);
    // applicant.setResume(resume);
    // applicant.setBirthDate(applicantDetails.getBirthDate());
    // applicant.setLocations(applicantDetails.getLocations());
    // applicant.setLanguages(applicantDetails.getLanguages());
    // applicant.setExperiences(applicantDetails.getExperiences());
    // applicant.setEducations(applicantDetails.getEducations());
    // applicant.setApplications(applicantDetails.getApplications());

    // return new
    // ResponseVOBuilder<Applicant>().success().addData(applicantRepository.save(applicant)).build();
    // }

    public ResponseVO<Number> getUserTotalApplications(Applicant applicant) {
        return applicationService.countApplicantApplications(applicant);
    }

    public ResponsePageableVO<Application> getApplications(Applicant applicant, int page, int size, String sortBy,
            String sortDirection, LocalDateTime startDate, LocalDateTime endDate) {
        return applicationService.getUserApplications(applicant, page, size, sortBy, sortDirection, startDate, endDate);
    }

    public ResponseVO<Number> countUserAppliedJobStatus(String q, Applicant applicant) {
        return new ResponseVOBuilder<Number>().success().addData(
                applicationService.countUserAppliedJobStatus(ApplicationStatus.valueOf(q.toUpperCase()), applicant))
                .build();
    }

    public void bookmarkJob(Long applicantId, Long jobId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        applicant.getBookmarkedJobs().add(job);
        applicantRepository.save(applicant);
    }

    public void removeBookmark(Long applicantId, Long jobId) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        applicant.getBookmarkedJobs().remove(job);
        applicantRepository.save(applicant);
    }

    public ResponsePageableVO<Job> getBookmarkedJobs(Long applicantId, int page, int size) {

        Applicant applicantEntity = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        List<Job> bookmarkedJobs = new ArrayList<>(applicantEntity.getBookmarkedJobs());

        int start = page * size;
        int end = Math.min(start + size, bookmarkedJobs.size());

        List<Job> pageContent = bookmarkedJobs.subList(start, end);

        Page<Job> jobsPage = new PageImpl<>(pageContent, PageRequest.of(page, size), bookmarkedJobs.size());

        return new ResponsePageableVO<>(jobsPage.getContent(), jobsPage.getNumber(), jobsPage.getSize(),
                jobsPage.getTotalElements(), jobsPage.getTotalPages(), jobsPage.isLast());
    }

    public ResponseVO<Applicant> updateDetails(Applicant applicant, ApplicantDto detailsDto) {
        if (detailsDto.getFullName() != null) {
            applicant.setFullName(detailsDto.getFullName());
        }
        if (detailsDto.getAbout() != null) {
            applicant.setAbout(detailsDto.getAbout());
        }
        if (detailsDto.getFacebook() != null) {
            applicant.setFacebook(detailsDto.getFacebook());
        }
        if (detailsDto.getLinkedin() != null) {
            applicant.setLinkedin(detailsDto.getLinkedin());
        }
        if (detailsDto.getTwitter() != null) {
            applicant.setTwitter(detailsDto.getTwitter());
        }
        if (detailsDto.getLocation() != null) {
            applicant.setLocation(detailsDto.getLocation());
        }
        if (detailsDto.getPortfolio() != null) {
            applicant.setPortfolio(detailsDto.getPortfolio());
        }

        return new ResponseVOBuilder<Applicant>().success().addData(applicantRepository.save(applicant)).build();
    }

    public ResponseVO<Experience> addExperience(Applicant applicant, ExperienceDto experienceDto) {
        Experience experience = experienceService.createExperience(experienceDto);
        applicant.getExperiences().add(experience);
        applicantRepository.save(applicant);
        return new ResponseVOBuilder<Experience>().success().addData(experience)
                .build();
    }

    public ResponseVO<Education> addEducation(Applicant applicant, EducationDto educationDto) {
        Education education = educationService.createEducation(educationDto);
        applicant.getEducations().add(education);
        applicantRepository.save(applicant);
        return new ResponseVOBuilder<Education>().success().addData(education)
                .build();
    }

    public ResponseVO<Set<Skill>> addSkills(Applicant applicant, String[] skills) {
        Set<Skill> newSkills = new HashSet<>();
        for (String skill : skills) {
            Skill newSkill = skillService.createSkill(skill, applicant);
            newSkills.add(newSkill);
        }
        return new ResponseVOBuilder<Set<Skill>>().success().addData(newSkills).build();
    }

    public ResponseVO<String> updateProfile(Applicant applicant, MultipartFile file) {
        applicant.setProfilePicture(fileStorageService.store(file));
        applicantRepository.save(applicant);
        return new ResponseVOBuilder<String>().success().addData("Profile picture updated susccessfully!!").build();
    }

    public ResponseVO<Education> updateEducation(Long id, EducationDto educationDto) {
        return new ResponseVOBuilder<Education>().success().addData(educationService.updateEducation(id, educationDto))
                .build();
    }

    public ResponseVO<Experience> updateExperience(Long id, ExperienceDto experienceDto) {
        return new ResponseVOBuilder<Experience>().success()
                .addData(experienceService.updateExperience(id, experienceDto))
                .build();
    }

    public ResponseVO<String> deleteEducation(Long id) {
        return new ResponseVOBuilder<String>().success().addData(educationService.deleteEducation(id)).build();
    }

    public ResponseVO<String> deleteExperience(Long id) {
        return new ResponseVOBuilder<String>().success().addData(experienceService.deleteExperience(id)).build();
    }

    public ResponseVO<String> removeskill(Applicant applicant, Long id) {
        try {
            Skill skill = skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
            applicant.getSkills().remove(skill);
            return new ResponseVOBuilder<String>().success().addData("Skill removed successfully").build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}