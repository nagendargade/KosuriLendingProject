package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.service.CustomerLoginService;
import com.example.kosuriTask.serviceImpl.CustomerRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customerLogin")
public class CustomerLoginController {

    @Autowired
    private CustomerLoginService customerLoginService;
    @Autowired
    private CustomerRegistrationServiceImpl registrationService;




//    @PostMapping("/customerLogin")
//    public ResponseEntity<CustomerLoginDto> loginCustomer(@RequestBody CustomerLoginDto loginDto) {
//        try {
//            CustomerLoginDto loggedInCustomer = customerLoginService.withEmailOrPhoneNumber(loginDto);
//            return new ResponseEntity<>(loggedInCustomer, HttpStatus.OK);
//        } catch (ExceptionHandling e) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }

}
