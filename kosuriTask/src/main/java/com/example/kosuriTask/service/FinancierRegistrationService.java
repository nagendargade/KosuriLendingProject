package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.CustomerRegistrationDto;
import com.example.kosuriTask.dto.FinancierRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface FinancierRegistrationService extends UserDetailsService {

    String registerFinancier(FinancierRegistrationDto financierRegistrationDto);

}
