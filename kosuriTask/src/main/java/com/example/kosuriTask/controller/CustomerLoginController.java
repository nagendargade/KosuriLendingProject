package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customrLogin")
public class CustomerLoginController {

    @Autowired
    private CustomerLoginService customerLoginService;


    @PostMapping("/login")
    public ResponseEntity<CustomerLoginDto> custmrLogIn(@RequestBody CustomerLoginDto customerLoginDto){
        return new ResponseEntity<>(customerLoginService.withEmailOrPhoneNumber(customerLoginDto), HttpStatus.OK);
    }

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
