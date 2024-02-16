package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.AdminLoginDto;
import com.example.kosuriTask.entity.AdminLogIn;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.AdminLogInRepo;
import com.example.kosuriTask.service.AdminLogInService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminLogInServiceImpl implements AdminLogInService {
    private final AdminLogInRepo  adminLogInRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public AdminLogInServiceImpl(AdminLogInRepo adminLogInRepo,
                                 ModelMapper modelMapper,
                                 BCryptPasswordEncoder bCryptPasswordEncoder){
        this.adminLogInRepo=adminLogInRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }


    @Override
    public AdminLoginDto loginWithEmailAndPhoneNumber(AdminLoginDto adminLoginDto) {
        AdminLogIn adminLogIn = adminLogInRepo.findByEmailOrPhoneNumber(adminLoginDto.getEmail(), adminLoginDto.getPhoneNumber())
                .orElseThrow(() -> new ExceptionHandling("Invalid credentials"));

        // Check if the provided password matches the stored password
        if (passwordMatches(adminLoginDto.getPassword(), adminLogIn.getPassword())) {
            return modelMapper.map(adminLogIn, AdminLoginDto.class);
        } else {
            throw new ExceptionHandling("Invalid credentials");
        }
    }

    @Override
    public AdminLoginDto changePassword(AdminLoginDto adminLoginDto, String email, String phoneNumber) {
        AdminLogIn adminLogin = adminLogInRepo.findByEmailOrPhoneNumber(email, phoneNumber)
                .orElseThrow(() -> new ExceptionHandling("Admin not found"));

        if (adminLoginDto.getOldPassword() == null) {
            throw new IllegalArgumentException("Old password cannot be null");
        }

        // Check if the provided old password matches the stored password
        if (passwordMatches(adminLoginDto.getOldPassword(), adminLogin.getPassword())) {
            // Update the password with the new one (if it's not already BCrypt-encoded)
            String newPassword = adminLoginDto.getPassword();
            if(newPassword!=null){
                if(!isAdminPasswordBCryptEncoded(newPassword)) {
                    newPassword = bCryptPasswordEncoder.encode(newPassword);
                }
                adminLogin.setPassword(newPassword);
                adminLogInRepo.save(adminLogin);
                return modelMapper.map(adminLogin, AdminLoginDto.class);
            } else {
                throw new IllegalArgumentException("New password cannot be null");
            }
        }else{
                throw new  ExceptionHandling("Old password is incorrect");
            }

    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return isAdminPasswordBCryptEncoded(encodedPassword)
                ? bCryptPasswordEncoder.matches(rawPassword, encodedPassword)
                : rawPassword.equals(encodedPassword);
    }

    private boolean isAdminPasswordBCryptEncoded(String password) {
        // Check if the password starts with the BCrypt identifier
        return password != null && password.startsWith("$2a$");

    }















//
//    @Override
//    public AdminLoginDto loginWithEmailAndPhoneNumber(AdminLoginDto adminLoginDto) {
//        AdminLogIn adminLogIn = adminLogInRepo.findByEmailOrPhoneNumber(adminLoginDto.getEmail(),adminLoginDto.getPhoneNumber())
//                .orElseThrow(()-> new ExceptionHandling("Invalid credentials"));
//        if(bCryptPasswordEncoder.matches(adminLoginDto.getPassword(), adminLogIn.getPassword())){
//            return modelMapper.map(adminLogIn, AdminLoginDto.class);
//        }else{
//            throw new ExceptionHandling("Invalid credentials");
//        }
//
//    }
//
//    @Override
//    public AdminLoginDto changePassword(AdminLoginDto adminLoginDto, String email, String phoneNumber) {
//        AdminLogIn adminLogin = adminLogInRepo.findByEmailOrPhoneNumber(email,phoneNumber)
//                .orElseThrow(() -> new ExceptionHandling("Admin not found"));
//        if(adminLoginDto.getOldPassword()==null){
//            throw new IllegalArgumentException("Old password cannot be null");
//        }
//
//        // Check if the provided old password matches the stored password
//        if (bCryptPasswordEncoder.matches(adminLoginDto.getOldPassword(), adminLogin.getPassword())) {
//            // Update the password with the new one
//            String newPassword=bCryptPasswordEncoder.encode(adminLoginDto.getPassword());
//            adminLogin.setPassword(newPassword);
//            adminLogInRepo.save(adminLogin);
//            return modelMapper.map(adminLogin, AdminLoginDto.class);
//        } else {
//            throw new ExceptionHandling("Old password is incorrect");
//        }
//    }

}
