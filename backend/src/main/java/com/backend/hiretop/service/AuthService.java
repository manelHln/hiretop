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
import com.backend.hiretop.dto.LoginResponseDto;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseVO<String> registerApplicant(ApplicantDto request) {
        try {
            if (emailExists(request.getEmail()))
                throw new UserAuthenticationException(
                        "This email is already registered with an account. Login with your credentials instead!!");

            Applicant user = Applicant.builder()
                    .fullName(request.getFullName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.APPLICANT)
                    .email(request.getEmail())
                    .birthDate(request.getBirthDate())
                    .gender(Gender.valueOf(request.getGender().toUpperCase()))
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

            Company user = Company.builder()
                    .name(request.getName())
                    .companyWebsite(request.getCompanyWebsite())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
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

    public ResponseVO<LoginResponseDto> loginApplicant(LoginRequestDto request) {
        Applicant applicant = applicantRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No applicant is registered with this email. Create an account instead!!"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtService.generateToken(applicant);
        LoginResponseDto result = new LoginResponseDto();
        result.setEmail(applicant.getEmail());
        result.setName(applicant.getFullName());
        result.setToken(token);
        result.setRole(applicant.getRole().toString());
        result.setImage(applicant.getProfilePicture());
        return new ResponseVOBuilder<LoginResponseDto>().success().addData(result).build();
    }

    public ResponseVO<LoginResponseDto> loginCompany(LoginRequestDto request) {
        Company company = companyRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No company is registered with this email. Create an account instead!!"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtService.generateToken(company);
        LoginResponseDto result = new LoginResponseDto();
        result.setEmail(company.getEmail());
        result.setName(company.getName());
        result.setToken(token);
        result.setRole(company.getRole().toString());
        result.setImage(company.getLogo());
        return new ResponseVOBuilder<LoginResponseDto>().success().addData(result).build();
    }
}
