package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.EmiPatternDto;

import java.util.List;

public interface EmiPatternService {
    EmiPatternDto saveEmiPattern(EmiPatternDto emiPatternDto,String contactEmail);
    List<EmiPatternDto> getEmiPatternById(String contactEmail);
    EmiPatternDto updateEmiPattern(long emiId, EmiPatternDto emiPattern, String contactEmail);
}
