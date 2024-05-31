package com.backend.hiretop.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "education")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "university")
    private String university;

    @Column(name = "diploma")
    private String diploma;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "from_date")
    @Temporal(value = TemporalType.DATE)
    private Date from;

    @Column(name = "to_date")
    @Temporal(value = TemporalType.DATE)
    private Date to;
}
