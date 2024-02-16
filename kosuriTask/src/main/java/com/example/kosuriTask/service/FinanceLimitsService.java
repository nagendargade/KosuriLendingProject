package com.example.kosuriTask.service;



import com.example.kosuriTask.dto.FinanceLimitsDto;

import java.util.List;

public interface FinanceLimitsService {
    FinanceLimitsDto addFinanceLimits(FinanceLimitsDto financeLimits, String contactEmail);
    List<FinanceLimitsDto> getByContactEmail(String contactEmail);
    FinanceLimitsDto updateByContactEmail(FinanceLimitsDto updatedFinanceLimits, String contactEmail, long finLimtId);

}
