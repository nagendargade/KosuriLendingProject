package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.LoginResponseDto;
import com.example.kosuriTask.dto.RegistrationResponseDto;
import com.example.kosuriTask.dto.FinancierLogInDto;
import com.example.kosuriTask.dto.FinancierRegistrationDto;

public interface FinancierRegistrationService {

    RegistrationResponseDto registerFinancier(FinancierRegistrationDto financierRegistrationDto);

    LoginResponseDto loginFinancier(FinancierLogInDto loginDto);



}
