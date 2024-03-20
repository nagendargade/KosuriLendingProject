package com.example.kosuriTask.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private RegistrationResponseDto Registration;
    private  String jwtToken;

}
