package com.backend.hiretop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Industry;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.repository.IndustryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndustryService {
    private final IndustryRepository industryRepository;
    public ResponseVO<List<Industry>> getAllIndustries() {
        return new ResponseVOBuilder<List<Industry>>().success().addData(industryRepository.findAll()).build();
    }
}
