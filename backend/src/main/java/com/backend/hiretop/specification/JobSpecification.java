package com.backend.hiretop.specification;

import org.springframework.data.jpa.domain.Specification;

import com.backend.hiretop.domain.Category;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.dto.JobFilterDto;
import com.backend.hiretop.enums.JobLevel;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.List;

public class JobSpecification {

    public static Specification<Job> filterByCriteria(JobFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filterDto.getJobTitle() != null && !filterDto.getJobTitle().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")),
                                "%" + filterDto.getJobTitle().toLowerCase() + "%"));
            }

            if (filterDto.getSalaryFrom() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("salaryFrom"), filterDto.getSalaryFrom()));
            }

            if (filterDto.getSalaryTo() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("salaryTo"), filterDto.getSalaryTo()));
            }

            if (filterDto.getCreatedDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), filterDto.getCreatedDate()));
            }

            if (filterDto.getJobLevel() != null && !filterDto.getJobLevel().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("jobLevel"),
                                JobLevel.valueOf(filterDto.getJobLevel().toUpperCase())));
            }

            if (filterDto.getCategories() != null && !filterDto.getCategories().isEmpty()) {
                Join<Job, Category> categories = root.join("categories", JoinType.INNER);
                predicate = criteriaBuilder.and(predicate, categories.get("name").in(filterDto.getCategories()));
            }

            if (filterDto.getSkills() != null && !filterDto.getSkills().isEmpty()) {
                Join<Job, Skill> skills = root.join("skills", JoinType.INNER);
                predicate = criteriaBuilder.and(predicate, skills.get("name").in(filterDto.getSkills()));
            }

            return predicate;
        };
    }
}
