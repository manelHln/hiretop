package com.backend.hiretop.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobFilterDto {
    private List<String> categories;
    private List<String> skills;
    private String jobTitle;
    private Double salaryFrom;
    private Double salaryTo;
    private LocalDate createdDate;
    private String jobLevel;
}
