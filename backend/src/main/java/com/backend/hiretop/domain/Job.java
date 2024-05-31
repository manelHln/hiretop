package com.backend.hiretop.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.backend.hiretop.enums.JobLevel;
import com.backend.hiretop.enums.JobStatus;
import com.backend.hiretop.enums.JobType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "level")
    @Enumerated(value = EnumType.ORDINAL)
    private JobLevel jobLevel;

    @Column(name = "salary_from")
    private Double salaryFrom;

    @Column(name = "salary_to")
    private Double salaryTo;

    @Column(name = "job_view")
    private int jobViews;

    @Column(name = "type")
    @Enumerated(value = EnumType.ORDINAL)
    private JobType jobType;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private JobStatus status;

    @Column(name = "location")
    private String location;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "job_skill",
               joinColumns = @JoinColumn(name = "job_id"),
               inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "job_category",
               joinColumns = @JoinColumn(name = "job_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Application> applications = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
