package com.example.kosuriTask.dto;

import com.example.kosuriTask.entity.enumValues.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationDto {
    private long cUserId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String state;
    private String district;
    private String location;
    private String pinCode;
    private UserType userType;
    private Boolean status;

    //private String jwtToken;

}
