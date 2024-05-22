package com.backend.hiretop.dto;

import java.util.Set;

import com.backend.hiretop.domain.Category;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.enums.JobLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.backend.hiretop.enums.JobType;

@Setter
@Getter
@Builder
public class JobDto {
    private String jobTitle;

    private String description;

    private String responsibilities;

    private String whoYouAre;

    private String niceToHave;

    private String jobLevel;

    private Number salary;

    private String jobType;

    private Set<String> skills;
    
    private Set<String> categories;

}
