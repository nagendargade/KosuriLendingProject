package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.LoginResponseDto;
import com.example.kosuriTask.dto.RegistrationResponseDto;
import com.example.kosuriTask.dto.FinancierLogInDto;
import com.example.kosuriTask.dto.FinancierRegistrationDto;
import com.example.kosuriTask.entity.FinancierLogIn;
import com.example.kosuriTask.entity.FinancierRegistration;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.FinancierLogInRepo;
import com.example.kosuriTask.repository.FinancierRegistrationRepo;
import com.example.kosuriTask.security.config.JwtService;
import com.example.kosuriTask.service.FinancierRegistrationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class FinancierRegistrationServiceImpl implements FinancierRegistrationService {
    private final FinancierRegistrationRepo registrationRepo;
    private final FinancierLogInRepo financierLogInRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

//    @Value("${application.security.jwt.secret-key}")
//    private String secretKey;
//    @Value("${application.security.jwt.expiration}")
//    private long jwtExpiration;
//    @Value("${application.security.jwt.refresh-token.expiration}")
//    private long refreshExpiration;



    @Autowired
    public FinancierRegistrationServiceImpl(FinancierRegistrationRepo registrationRepo,
                                            FinancierLogInRepo financierLogInRepo,
                                            ModelMapper modelMapper,
                                            BCryptPasswordEncoder bCryptPasswordEncoder,
                                            JwtService jwtService){
        this.registrationRepo=registrationRepo;
        this.financierLogInRepo=financierLogInRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtService=jwtService;
    }

    @Override
    public RegistrationResponseDto registerFinancier(FinancierRegistrationDto financierRegistrationDto) {

        if (financierRegistrationDto.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        FinancierRegistration registration = modelMapper.map(financierRegistrationDto, FinancierRegistration.class);

        if (registrationRepo.existsByEmail(registration.getEmail())) {
            throw new ExceptionHandling("Email already exists: " + registration.getEmail());
        }

        String encodedPassword = bCryptPasswordEncoder.encode(registration.getPassword());
        registration.setPassword(encodedPassword);

        FinancierRegistration savedRegistration = registrationRepo.save(registration);

        FinancierLogIn financierLogIn=modelMapper.map(savedRegistration, FinancierLogIn.class);
        financierLogIn.setStatus(true);
        financierLogInRepo.save(financierLogIn);

        return modelMapper.map(savedRegistration, RegistrationResponseDto.class);

    }


    @Override
    public LoginResponseDto loginFinancier(FinancierLogInDto loginDto) {
        FinancierRegistration financierRegistration = getFinancierLogin(loginDto);
        if (financierRegistration == null || !bCryptPasswordEncoder.matches(loginDto.getPassword(), financierRegistration.getPassword())) {
            throw new ExceptionHandling("Invalid credentials");
        }

        String jwtToken = jwtService.generateToken(financierRegistration);
        LoginResponseDto responseDto= new LoginResponseDto();
        responseDto.setRegistration(modelMapper.map(financierRegistration, RegistrationResponseDto.class));
        responseDto.setJwtToken(jwtToken);

        return responseDto;
    }

    private FinancierRegistration getFinancierLogin(FinancierLogInDto loginDto) {
        FinancierRegistration financierRegistration = null;

        if (isValidEmail(loginDto.getEmail())) {
            financierRegistration = registrationRepo.findByEmail(loginDto.getEmail()).orElse(null);
        }

        if (financierRegistration == null && isValidPhoneNumber(loginDto.getPhoneNumber())) {
            financierRegistration = registrationRepo.findByPhoneNumber(loginDto.getPhoneNumber()).orElse(null);
        }

        if (financierRegistration == null) {
            throw new UsernameNotFoundException("Invalid login details");
        }

        return financierRegistration;
    }

    private boolean isValidPhoneNumber(String input) {
        return input != null && input.matches("\\d{10}");
    }

    private boolean isValidEmail(String input){
        return input != null && input.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }



}
