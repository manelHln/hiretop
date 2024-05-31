package com.backend.hiretop.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicantDto {
    private String password;
    private String gender;
    private String fullName;
    private String phoneNumber;
    private String location;
    private String about;
    private String portfolio;
    private String facebook;
    private String linkedin;
    private String twitter;
    private String email;
    private String profilePicture;
    private Date birthDate;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;
    private List<String> skills;
}
