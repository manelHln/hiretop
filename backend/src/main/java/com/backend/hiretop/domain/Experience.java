package com.backend.hiretop.domain;

import java.util.Date;

import com.backend.hiretop.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "company")
    private String company;

    @Column(name = "job_type")
    @Enumerated(value = EnumType.ORDINAL)
    private JobType jobType;

    @Column(name = "from_date")
    @Temporal(value = TemporalType.DATE)
    private Date from;

    @Column(name = "to_date")
    @Temporal(value = TemporalType.DATE)
    private Date to;

    @Column(name = "location")
    private String location;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
