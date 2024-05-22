package com.backend.hiretop.service;

import com.backend.hiretop.domain.Category;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.dto.JobDto;
import com.backend.hiretop.dto.ResponsePageableVO;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.enums.ApplicationStatus;
import com.backend.hiretop.enums.JobLevel;
import com.backend.hiretop.enums.JobStatus;
import com.backend.hiretop.enums.JobType;
import com.backend.hiretop.repository.ApplicationRepository;
import com.backend.hiretop.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompanyService companyService;

    public ResponseVO<Job> createJob(Company loggedInCompany, JobDto job) {

        Company company = companyService.getCompanyWithJobs(loggedInCompany.getId());

        Job newJob = Job.builder()
                .description(job.getDescription())
                .jobLevel(JobLevel.valueOf(job.getJobLevel().toUpperCase()))
                .jobTitle(job.getJobTitle())
                .jobType(JobType.valueOf(job.getJobType().toUpperCase()))
                .niceToHave(job.getNiceToHave())
                .responsibilities(job.getResponsibilities())
                .salary(job.getSalary())
                .whoYouAre(job.getWhoYouAre())
                .company(company)
                .build();

        Set<Category> categories = new HashSet<>();

        if (job.getCategories() != null && !job.getCategories().isEmpty()) {
            categories = job.getCategories().stream().map(category -> categoryService.createCategory(category, newJob))
                    .collect(Collectors.toSet());
        }

        Set<Skill> skills = new HashSet<>();

        if (job.getSkills() != null && !job.getSkills().isEmpty()) {
            skills = job.getSkills().stream().map(skill -> skillService.createSkill(skill, newJob))
                    .collect(Collectors.toSet());
        }

        newJob.getCategories().addAll(categories);
        newJob.getSkills().addAll(skills);

        Job created = jobRepository.save(newJob);

        return new ResponseVOBuilder<Job>().success().addData(created).build();
    }

    public ResponseVO<Job> getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There was an error retrieving job!!"));
        job.setJobViews(job.getJobViews() + 1);
        jobRepository.save(job);
        return new ResponseVOBuilder<Job>().success().addData(job).build();
    }

    public ResponsePageableVO<Job> getAllJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Job> jobs = jobRepository.findAll(pageable);
        if (jobs.isEmpty()) {
            return new ResponsePageableVO<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        return new ResponsePageableVO<>(jobs.getContent(), jobs.getNumber(), jobs.getSize(),
                jobs.getTotalElements(), jobs.getTotalPages(), jobs.isLast());
    }

    public ResponsePageableVO<Job> getJobByCategoy(String category, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Job> jobs = jobRepository.findByCategoryName(category, pageable);
        if (jobs.isEmpty()) {
            return new ResponsePageableVO<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        return new ResponsePageableVO<>(jobs.getContent(), jobs.getNumber(), jobs.getSize(),
                jobs.getTotalElements(), jobs.getTotalPages(), jobs.isLast());
    }

    public Job updateJob(Long id, Job jobDetails) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        job.setJobTitle(jobDetails.getJobTitle());
        job.setDescription(jobDetails.getDescription());
        job.setResponsibilities(jobDetails.getResponsibilities());
        job.setWhoYouAre(jobDetails.getWhoYouAre());
        job.setNiceToHave(jobDetails.getNiceToHave());
        job.setJobLevel(jobDetails.getJobLevel());
        job.setSalary(jobDetails.getSalary());
        job.setJobType(jobDetails.getJobType());
        job.setSkills(jobDetails.getSkills());
        job.setCategories(jobDetails.getCategories());
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public ResponseVO<Number> countJobByStatus(Company company, String status) {
        return new ResponseVOBuilder<Number>().success()
                .addData(jobRepository.countByCompanyAndStatus(company, JobStatus.valueOf(status.toUpperCase())))
                .build();
    }

    public ResponseVO<Number> countJobApplied(Company company, String statusString) {
        return new ResponseVOBuilder<Number>().success().addData(applicationRepository
                .countByStatusAndCompanyId(ApplicationStatus.valueOf(statusString), company.getId())).build();
    }
}
