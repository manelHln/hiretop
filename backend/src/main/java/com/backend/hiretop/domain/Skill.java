package com.backend.hiretop.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Builder.Default
    @JsonIgnore
    @ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL)
    private Set<Job> jobs = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    @ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL)
    private Set<Applicant> applicants = new HashSet<>();
}
