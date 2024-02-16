package com.example.kosuriTask.entity;

import com.example.kosuriTask.entity.enumValues.UserType;

public interface Registration {
    long getId();
    String getFullName();
    String getEmail();
    String getPhoneNumber();
    String getPassword();
    String getState();
    String getDistrict();
    String getLocation();
    String getPinCode();
    UserType getUserType();
    Boolean status();


}
