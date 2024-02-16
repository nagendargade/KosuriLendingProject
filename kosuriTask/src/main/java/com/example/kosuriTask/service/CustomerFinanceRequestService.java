package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.CustomerFinanceRequestDto;

import java.util.List;

public interface CustomerFinanceRequestService {
    CustomerFinanceRequestDto addCustmrFincrReqst(CustomerFinanceRequestDto customerFinanceRequestDto,String financierId);
    List<CustomerFinanceRequestDto> getCustmrFincrDetails(String financier);
    CustomerFinanceRequestDto updateCustmrFincrDetails(CustomerFinanceRequestDto customerFinanceRequestDto, String financierId, String cuUserId);

}
