package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.dto.CustomerRegistrationDto;
import com.example.kosuriTask.dto.RegistrationResponseDto;
import com.example.kosuriTask.entity.CustomerRegistration;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.CustomerLoginRepo;
import com.example.kosuriTask.repository.CustomerRegistrationRepo;
import com.example.kosuriTask.security.config.JwtService;
import com.example.kosuriTask.service.CustomerRegistrationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final CustomerRegistrationRepo registrationRepo;
    private final CustomerLoginRepo loginRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;
    @Autowired
    public CustomerRegistrationServiceImpl(
            CustomerRegistrationRepo registrationRepo,
            CustomerLoginRepo loginRepo,
            ModelMapper modelMapper,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtService jwtService) {
        this.registrationRepo = registrationRepo;
        this.loginRepo = loginRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtService = jwtService;

    }

    @Override
    public RegistrationResponseDto registerCustomer(CustomerRegistrationDto registrationDto) {

        if(registrationDto.getPassword()==null){
            throw new IllegalArgumentException("Password cannot be null");
        }

        CustomerRegistration registration=modelMapper.map(registrationDto, CustomerRegistration.class);

        if(registrationRepo.existsByEmail(registration.getEmail())){
            throw new ExceptionHandling("email already exits"+registration.getEmail());
        }
        String encoderPassword= bCryptPasswordEncoder.encode(registration.getPassword());
        registration.setPassword(encoderPassword);
        CustomerRegistration saveCustomer=registrationRepo.save(registration);
        return modelMapper.map(saveCustomer, RegistrationResponseDto.class);
    }


//
       @Override
       public String loginCustomer(CustomerLoginDto loginDto) {
        CustomerRegistration customerRegistration = getCustomerRegistration(loginDto);
        // Check if customer exists and credentials are valid
        if (customerRegistration == null || !bCryptPasswordEncoder.matches(loginDto.getPassword(), customerRegistration.getPassword())) {
            throw new ExceptionHandling("Invalid credentials");
        }
        // Generate JWT token for the authenticated user
        String jwtToken = jwtService.generateToken(customerRegistration);

        // Save the token to the token repository if needed

        return jwtToken;
    }

    private CustomerRegistration getCustomerRegistration(CustomerLoginDto loginDto) {
        CustomerRegistration customerRegistration;
        if (isValidEmail(loginDto.getEmail())) {
            customerRegistration = registrationRepo.findByEmail(loginDto.getEmail()).orElse(null);
        } else if(isValidPhoneNumber(loginDto.getPhoneNumber())) {
            customerRegistration = registrationRepo.findByPhoneNumber(loginDto.getPhoneNumber()).orElse(null);
        }else{
            throw new UsernameNotFoundException("Invalid login input");
        }
        return customerRegistration;
    }


    private boolean isValidPhoneNumber(String input) {
        return input != null && input.matches("\\d{10}");
    }


    private boolean isValidEmail(String input){
        return input != null && input.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }






}
