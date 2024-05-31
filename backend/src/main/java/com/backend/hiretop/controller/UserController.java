package com.backend.hiretop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<ResponseVO<UserDetails>> getUserDeatails(@AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(new ResponseVOBuilder<UserDetails>().success().addData(user).build());
    }
}
