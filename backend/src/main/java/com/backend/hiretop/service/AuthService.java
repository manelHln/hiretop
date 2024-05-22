package com.backend.hiretop.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.PhoneContact;
import com.backend.hiretop.dto.ApplicantDto;
import com.backend.hiretop.dto.CompanyDto;
import com.backend.hiretop.dto.LoginRequestDto;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.enums.Gender;
import com.backend.hiretop.enums.UserRole;
import com.backend.hiretop.exception.UserAuthenticationException;
import com.backend.hiretop.repository.ApplicantRepository;
import com.backend.hiretop.repository.CompanyRepository;
import com.backend.hiretop.repository.PhoneContactRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ApplicantRepository applicantRepository;
    private final CompanyRepository companyRepository;
    private final JwtService jwtService;
    private final PhoneContactRepository phoneContactRepository;
    private final FileStorageService fileStorageService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseVO<String> registerApplicant(ApplicantDto request) {
        try {
            if (emailExists(request.getEmail()))
                throw new UserAuthenticationException(
                        "This email is already registered with an account. Login with your credentials instead!!");

            // Set<PhoneContact> phoneContacts = new HashSet<>();

            // if (request.getContacts() != null && !request.getContacts().isEmpty()) {
            //     phoneContacts = request.getContacts().stream()
            //             .map(contact -> {
            //                 PhoneContact newContact = PhoneContact.builder()
            //                         .contact(contact.getContact())
            //                         .type(contact.getType())
            //                         .build();
            //                 return phoneContactRepository.save(newContact);
            //             })
            //             .collect(Collectors.toSet());
            // }

            String profilePicture = "";

            if (request.getProfilePicture() != null && !request.getProfilePicture().isEmpty()) {
                profilePicture = fileStorageService.store(request.getProfilePicture());
            }

            String resume = "";

            if (request.getResume() != null && !request.getResume().isEmpty()) {
                resume = fileStorageService.store(request.getResume());
            }

            Applicant user = Applicant.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.APPLICANT)
                    .email(request.getEmail())
                    .about(request.getAbout())
                    .birthDate(request.getBirthDate())
                    .gender(Gender.valueOf(request.getGender().toUpperCase()))
                    // .contacts(phoneContacts)
                    .profilePicture(profilePicture)
                    .resume(resume)
                    .build();
            applicantRepository.save(user);
            return new ResponseVOBuilder<String>().success().addData(jwtService.generateToken(user)).build();
        } catch (UserAuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseVO<String> registerCompany(CompanyDto request) {
        try {
            if (emailExists(request.getEmail()))
                throw new UserAuthenticationException(
                        "This email is already registered with an account. Login with your credentials instead!!");

            String logo = "";

            if (request.getLogo() != null && !request.getLogo().isEmpty()) {
                logo = fileStorageService.store(request.getLogo());
            }

            // Set<PhoneContact> phoneContacts = new HashSet<>();

            // if (request.getContacts() != null && !request.getContacts().isEmpty()) {
            //     phoneContacts = request.getContacts().stream()
            //             .map(contact -> {
            //                 PhoneContact newContact = PhoneContact.builder()
            //                         .contact(contact.getContact())
            //                         .type(contact.getType())
            //                         .build();
            //                 return phoneContactRepository.save(newContact);
            //             })
            //             .collect(Collectors.toSet());
            // }

            Company user = Company.builder()
                    .name(request.getName())
                    .companyWebsite(request.getCompanyWebsite())
                    .founded(request.getFounded())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .history(request.getHistory())
                    .emails(request.getEmails())
                    .addresses(request.getAddresses())
                    // .contacts(phoneContacts)
                    .industries(request.getIndustries())
                    .logo(logo)
                    .role(UserRole.COMPANY)
                    .build();
            companyRepository.save(user);
            return new ResponseVOBuilder<String>().success().addData(jwtService.generateToken(user)).build();
        } catch (UserAuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean emailExists(String email) {
        return companyRepository.existsByEmail(email) || applicantRepository.existsByEmail(email);
    }

    public ResponseVO<String> loginApplicant(LoginRequestDto request) {
        Applicant applicant = applicantRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No applicant is registered with this email. Create an account instead!!"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new ResponseVOBuilder<String>().success().addData(jwtService.generateToken(applicant)).build();
    }

    public ResponseVO<String> loginCompany(LoginRequestDto request) {
        Company company = companyRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No company is registered with this email. Create an account instead!!"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new ResponseVOBuilder<String>().success().addData(jwtService.generateToken(company)).build();
    }
}
