package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.FinancierRegistrationDto;
import com.example.kosuriTask.service.FinancierRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/financierRegistration")
public class FinancierRegistrationController {
    private final FinancierRegistrationService financierRegistrationService;
    @Autowired
    public FinancierRegistrationController(FinancierRegistrationService financierRegistrationService){
        this.financierRegistrationService=financierRegistrationService;
    }


    @PostMapping("/register")
    public ResponseEntity<String>  registerFinancier(@RequestBody FinancierRegistrationDto dto){
        return new ResponseEntity<>(financierRegistrationService.registerFinancier(dto), HttpStatus.CREATED);
    }


}
