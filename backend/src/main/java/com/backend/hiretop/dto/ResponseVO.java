package com.backend.hiretop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> {
    private String status;
    private String result;
    private ResponseErrorVo error;
    private T content;
}
