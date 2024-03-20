package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.AdminLoginDto;
import com.example.kosuriTask.dto.AdminLoginRespDto;

public interface AdminLogInService {
    AdminLoginRespDto loginWithEmailAndPhoneNumber(AdminLoginRespDto adminLoginDto);

    AdminLoginDto changePassword(AdminLoginDto adminLoginDto, String email, String password);
}
