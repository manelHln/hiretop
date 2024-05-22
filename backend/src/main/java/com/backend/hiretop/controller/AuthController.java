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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.backend.hiretop.dto.LoginRequestDto;
import com.backend.hiretop.dto.ResponseVO;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "register/applicant", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseVO<String>> registerApplicant(@ModelAttribute @Valid ApplicantDto request) {
        return ResponseEntity.ok(authService.registerApplicant(request));
    }

    @PostMapping(path = "register/org", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseVO<String>> registerCompany(@ModelAttribute CompanyDto request) {
        return ResponseEntity.ok(authService.registerCompany(request));
    }

    @PostMapping("login/applicant")
    public ResponseEntity<ResponseVO<String>> loginApplicant(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.loginApplicant(request));
    }

    @PostMapping("login/org")
    public ResponseEntity<ResponseVO<String>> loginCompany(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.loginCompany(request));
    }

}
