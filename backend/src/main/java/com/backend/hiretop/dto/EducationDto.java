package com.backend.hiretop.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationDto {
    private String university;

    private String diploma;

    private String description;

    private Date from;

    private Date to;
}
