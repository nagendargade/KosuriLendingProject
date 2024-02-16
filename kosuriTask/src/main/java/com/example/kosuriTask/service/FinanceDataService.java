package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.FinanceDataDto;

import java.util.List;

public interface FinanceDataService {
    FinanceDataDto addFinanceData(FinanceDataDto financeDataDto, String contactEmail);
    List<FinanceDataDto> getFinanceDataByContactEmail(String contactEmail);

    FinanceDataDto updateFinanceDataByContEmail(FinanceDataDto financeDataDto,String contactEmail,long fincnId);

}
