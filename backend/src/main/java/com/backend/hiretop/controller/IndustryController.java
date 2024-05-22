package com.backend.hiretop.controller;

import com.backend.hiretop.domain.Industry;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.service.IndustryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industries")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    @GetMapping("/")
    public ResponseEntity<ResponseVO<List<Industry>>> getAllIndustries() {
        return ResponseEntity.ok(industryService.getAllIndustries());
    }
}