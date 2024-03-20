package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.repository.FinancierLogInRepo;
import com.example.kosuriTask.security.config.JwtService;
import com.example.kosuriTask.service.FinancierLogInService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FinancierLogInServiceImpl implements FinancierLogInService {
    private final FinancierLogInRepo financierLogInRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    @Autowired
    public FinancierLogInServiceImpl(FinancierLogInRepo financierLogInRepo,
                                     ModelMapper modelMapper,
                                     BCryptPasswordEncoder bCryptPasswordEncoder,
                                     JwtService jwtService){
        this.financierLogInRepo=financierLogInRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtService=jwtService;

    }


}
