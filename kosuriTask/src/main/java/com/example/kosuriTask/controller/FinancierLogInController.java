package com.example.kosuriTask.controller;


import com.example.kosuriTask.dto.FinancierLogInDto;
import com.example.kosuriTask.service.FinancierLogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financierLogin")
public class FinancierLogInController {
    @Autowired
    FinancierLogInService financierLogInService;


//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody FinancierLogInDto financierLogInDto) {
//        return new ResponseEntity<>(financierLogInService.loginFinancier(financierLogInDto), HttpStatus.OK);
//    }



}
