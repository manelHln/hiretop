package com.backend.hiretop.dto;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.backend.hiretop.domain.Address;
import com.backend.hiretop.domain.Industry;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.MailContact;
import com.backend.hiretop.domain.PhoneContact;
import com.backend.hiretop.domain.Skill;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompanyDto {
    private String name;

    private String email;

    private String password;

    private MultipartFile logo;

    private String history;

    private String companyWebsite;

    private Number size;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date founded;

    private Set<MailContact> otherEmails;

    private Set<Job> jobs;

    private Set<String> skills;

    private Set<String> industries;
}
