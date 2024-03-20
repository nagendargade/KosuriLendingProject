package com.example.kosuriTask.dto;

import lombok.Data;

@Data
public class AdminLoginRespDto {

    private long id;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean status;
    private String jwtToken;
}
