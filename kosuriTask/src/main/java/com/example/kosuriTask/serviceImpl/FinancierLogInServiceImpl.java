package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.FinancierLogInDto;
import com.example.kosuriTask.entity.FinancierLogIn;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.FinancierLogInRepo;
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
    @Autowired
    public FinancierLogInServiceImpl(FinancierLogInRepo financierLogInRepo,
                                     ModelMapper modelMapper,
                                     BCryptPasswordEncoder bCryptPasswordEncoder){
        this.financierLogInRepo=financierLogInRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public FinancierLogInDto loginWithEmailOrPhoneNumber(FinancierLogInDto financierLogInDto) {
        FinancierLogIn financierLogIn = financierLogInRepo.findByEmailOrPhoneNumber(financierLogInDto.getEmail(),financierLogInDto.getPhoneNumber())
                .orElseThrow(()-> new ExceptionHandling("Invalid credentials"));
        if(bCryptPasswordEncoder.matches(financierLogInDto.getPassword(),financierLogIn.getPassword())){
            return modelMapper.map(financierLogIn, FinancierLogInDto.class);
        }else{
            throw new ExceptionHandling("Invalid credentials");
        }
    }


}
