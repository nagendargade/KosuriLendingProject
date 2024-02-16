package com.example.kosuriTask.service;


import com.example.kosuriTask.dto.FinanceTenureDto;

import java.util.List;

public interface FinanceTenureService {
    FinanceTenureDto addFinanceTenure(FinanceTenureDto financeTenureDto, String contactEmail);
    List<FinanceTenureDto> getFinceTenureByContactEmail(String contactEmail);
    FinanceTenureDto updateByContEmail(FinanceTenureDto financeTenureDto,String contactEmail,long Id);

}
