package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.AdminLoginDto;

public interface AdminLogInService {
    AdminLoginDto loginWithEmailAndPhoneNumber(AdminLoginDto adminLoginDto);

    AdminLoginDto changePassword(AdminLoginDto adminLoginDto, String email, String password);
}
