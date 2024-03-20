package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.entity.CustomerLogIn;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.CustomerLoginRepo;
import com.example.kosuriTask.security.config.JwtService;
import com.example.kosuriTask.service.CustomerLoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

    private final CustomerLoginRepo customerLoginRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    @Autowired
    public CustomerLoginServiceImpl(CustomerLoginRepo customerLoginRepo,
                                    ModelMapper modelMapper,
                                    BCryptPasswordEncoder bCryptPasswordEncoder,
                                    JwtService jwtService){
        this.customerLoginRepo=customerLoginRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtService=jwtService;
    }

    @Override
    public CustomerLoginDto withEmailOrPhoneNumber(CustomerLoginDto customerLoginDto) {
        CustomerLogIn customerLogIn=customerLoginRepo.findByEmailOrPhoneNumber(customerLoginDto.getEmail(),customerLoginDto.getPhoneNumber())
                .orElseThrow(()-> new ExceptionHandling("Invalid credentials"));
        if(bCryptPasswordEncoder.matches(customerLoginDto.getPassword(),customerLogIn.getPassword())){
           return modelMapper.map(customerLogIn, CustomerLoginDto.class);

        }else{
            throw new ExceptionHandling("Invalid credentials");
        }
    }


















//    @Override
//    public CustomerLoginDto withEmailOrPhoneNumber(CustomerLoginDto customerLoginDto) {
//        CustomerLogIn  customerLogIn=modelMapper.map(customerLoginDto, CustomerLogIn.class);
//        if(isValidEmail(customerLoginDto.getEmail())){
//            customerLogIn=customerLoginRepo.findByEmailAndPassword(customerLogIn.getEmail(),customerLogIn.getPassword());
//        }else{
//            customerLogIn=customerLoginRepo.findByPhoneNumberAndPassword(customerLogIn.getPhoneNumber(),customerLogIn.getPassword());
//        }
//        return modelMapper.map(customerLogIn, CustomerLoginDto.class);
//    }
//    private boolean isValidEmail(String input) {
//        return input != null && input.contains("@");
//    }
}
