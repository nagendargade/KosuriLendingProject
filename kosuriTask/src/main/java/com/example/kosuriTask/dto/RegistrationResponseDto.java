package com.example.kosuriTask.dto;


import com.example.kosuriTask.entity.enumValues.UserType;
import lombok.Data;


@Data
public class RegistrationResponseDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String state;
    private String district;
    private String location;
    private String pinCode;
    private UserType userType;
    private Boolean status;

}
