package com.backend.hiretop.dto;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.backend.hiretop.domain.Address;
import com.backend.hiretop.domain.Application;
import com.backend.hiretop.domain.Education;
import com.backend.hiretop.domain.Experience;
import com.backend.hiretop.domain.Language;
import com.backend.hiretop.domain.MailContact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicantDto {
    private String firstname;

    private String lastname;

    private String gender;

    @Pattern(message = "Please provide a strong password", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    private String about;

    private Set<PhoneContactDto> contacts;

    @Email(message = "Please provide a valid email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private Set<MailContact> otherEmails;

    private MultipartFile profilePicture;

    private MultipartFile resume;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private Set<Address> locations;

    private Set<Language> languages;

    private Set<Experience> experiences;

    private Set<Education> educations;

    private Set<Application> applications;

}
