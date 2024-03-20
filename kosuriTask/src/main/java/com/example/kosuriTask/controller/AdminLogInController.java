package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.AdminLoginDto;
import com.example.kosuriTask.dto.AdminLoginRespDto;
import com.example.kosuriTask.entity.AdminLogIn;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.service.AdminLogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminLogInController {

    @Autowired
    private AdminLogInService adminLogInService;

    @PostMapping("/login")
    public ResponseEntity<AdminLoginRespDto> loginAdmin(@RequestBody AdminLoginRespDto adminLoginDto){
        return new ResponseEntity<>(adminLogInService.loginWithEmailAndPhoneNumber(adminLoginDto), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<AdminLoginDto> changePassword(
            @RequestBody AdminLoginDto adminLoginDto,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber) {
        try {
            if (email != null) {
                // Change password using email
                AdminLoginDto updatedAdmin = adminLogInService.changePassword(adminLoginDto, email, null);
                return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
            } else if (phoneNumber != null) {
                // Change password using phone number
                AdminLoginDto updatedAdmin = adminLogInService.changePassword(adminLoginDto, null, phoneNumber);
                return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
            } else {
                // Neither email nor phone number provided
                throw new ExceptionHandling("Email or phone number is required");
            }
        } catch (ExceptionHandling e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
