package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.dto.CustomerRegistrationDto;
import com.example.kosuriTask.entity.CustomerRegistration;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerRegistrationService extends UserDetailsService {
    String registerCustomer(CustomerRegistrationDto registrationDto);
}
