package com.backend.hiretop.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JobDto {
    private String jobTitle;

    private String description;

    private String jobLevel;

    private Double salaryFrom;

    private Double salaryTo;

    private String location;

    private String jobType;

    private Set<String> skills;
    
    private Set<String> categories;

}
