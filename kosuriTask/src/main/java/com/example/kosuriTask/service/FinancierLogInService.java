package com.example.kosuriTask.service;


import com.example.kosuriTask.dto.FinancierLogInDto;

public interface FinancierLogInService {
    FinancierLogInDto loginWithEmailOrPhoneNumber(FinancierLogInDto financierLogInDto);

}
