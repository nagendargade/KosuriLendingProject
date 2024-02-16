package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AdminLoginDto {
    private long id;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean status;
    private String oldPassword;
    private String newPassword;

}
