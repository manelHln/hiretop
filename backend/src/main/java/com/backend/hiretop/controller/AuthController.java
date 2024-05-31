package com.backend.hiretop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hiretop.dto.ApplicantDto;
import com.backend.hiretop.dto.CompanyDto;
import com.backend.hiretop.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.backend.hiretop.dto.LoginRequestDto;
import com.backend.hiretop.dto.LoginResponseDto;
import com.backend.hiretop.dto.ResponseVO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "register/applicant")
    public ResponseEntity<ResponseVO<String>> registerApplicant(@RequestBody @Valid ApplicantDto request) {
        return ResponseEntity.ok(authService.registerApplicant(request));
    }

    @PostMapping(path = "register/org")
    public ResponseEntity<ResponseVO<String>> registerCompany(@RequestBody CompanyDto request) {
        return ResponseEntity.ok(authService.registerCompany(request));
    }

    @PostMapping("login/applicant")
    public ResponseEntity<ResponseVO<LoginResponseDto>> loginApplicant(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.loginApplicant(request));
    }

    @PostMapping("login/org")
    public ResponseEntity<ResponseVO<LoginResponseDto>> loginCompany(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.loginCompany(request));
    }

}
