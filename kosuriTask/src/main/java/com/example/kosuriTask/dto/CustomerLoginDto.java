package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerLoginDto {
    private String email;
    private String phoneNumber;
    private String password;
}
