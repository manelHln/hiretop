package com.backend.hiretop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.hiretop.dto.ResponseErrorVo;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;

import lombok.SneakyThrows;

@RestControllerAdvice
public class RestExceptionHandlerAdvice {

    @SneakyThrows
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseVO> responseException(Exception ex) {
        return new ResponseEntity<>(new ResponseVOBuilder<>().fail().error(new ResponseErrorVo("500", ex.getLocalizedMessage())).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
