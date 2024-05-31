package com.backend.hiretop.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExperienceDto {
    private String role;
    private String company;
    private String jobType;
    private Date fromDate;
    private Date toDate;
    private String location;
    private String description;
}
