package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.dto.CustomerRegistrationDto;
import com.example.kosuriTask.service.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customerRegistration")
public class CustomerRegistrationController {
    private final CustomerRegistrationService registrationService;

    @Autowired
    public CustomerRegistrationController(CustomerRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrationDto registrationDTO) {
        return new ResponseEntity<>(registrationService.registerCustomer(registrationDTO), HttpStatus.CREATED);
    }



}
