package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FinancierLogInDto {
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean status;
}
