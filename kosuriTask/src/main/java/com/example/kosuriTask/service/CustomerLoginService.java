package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.dto.FinancierLogInDto;

public interface CustomerLoginService {
        CustomerLoginDto withEmailOrPhoneNumber(CustomerLoginDto customerLoginDto);


}
