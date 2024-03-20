package com.example.kosuriTask.controller;

import com.example.kosuriTask.dto.LoginResponseDto;
import com.example.kosuriTask.dto.RegistrationResponseDto;
import com.example.kosuriTask.dto.FinancierLogInDto;
import com.example.kosuriTask.dto.FinancierRegistrationDto;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.service.FinancierRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/financier")
public class FinancierRegistrationController {


    private final FinancierRegistrationService financierRegistrationService;

    @Autowired
    public FinancierRegistrationController(FinancierRegistrationService financierRegistrationService){
        this.financierRegistrationService=financierRegistrationService;
    }


//    @PostMapping("/register")
//    public ResponseEntity<RegistrationResponseDto>  registerFinancier(@RequestBody FinancierRegistrationDto dto){
//        return new ResponseEntity<>(financierRegistrationService.registerFinancier(dto), HttpStatus.CREATED);
//    }


    @PostMapping("/register")
    public ResponseEntity<Object> registerFinancier(@RequestBody FinancierRegistrationDto dto) {
        try {
            RegistrationResponseDto responseDto = financierRegistrationService.registerFinancier(dto);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException | ExceptionHandling exception) {
            // Handle specific exceptions
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            // Handle other exceptions
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





//    @PostMapping("/register")
//    public ResponseEntity<FinanceRegistrationResponseDto> registerFinancier(@RequestBody FinancierRegistrationDto dto) {
//        try {
//            FinanceRegistrationResponseDto responseDto = financierRegistrationService.registerFinancier(dto);
//            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
//        } catch (ExceptionHandling ex) {
//
//
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body();
//        }
//    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody FinancierLogInDto financierLogInDto) {
        return new ResponseEntity<>(financierRegistrationService.loginFinancier(financierLogInDto),HttpStatus.OK);
    }


}
