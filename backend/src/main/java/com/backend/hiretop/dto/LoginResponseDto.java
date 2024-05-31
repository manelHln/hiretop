package com.backend.hiretop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String name;
    private String email;
    private String token;
    private String role;
    private String image;
}
