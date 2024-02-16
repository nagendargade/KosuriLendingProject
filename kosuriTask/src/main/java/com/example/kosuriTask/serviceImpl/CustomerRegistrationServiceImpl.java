package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.CustomerLoginDto;
import com.example.kosuriTask.dto.CustomerRegistrationDto;

import com.example.kosuriTask.entity.CustomerLogIn;
import com.example.kosuriTask.entity.CustomerRegistration;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.CustomerLoginRepo;
import com.example.kosuriTask.repository.CustomerRegistrationRepo;
import com.example.kosuriTask.security.config.JwtService;
import com.example.kosuriTask.service.CustomerRegistrationService;
import com.example.kosuriTask.token.Token;
import com.example.kosuriTask.token.TokenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
@Primary
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {
    private final CustomerRegistrationRepo registrationRepo;
    private final CustomerLoginRepo loginRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;




    @Autowired
    public CustomerRegistrationServiceImpl(
            CustomerRegistrationRepo registrationRepo,
            CustomerLoginRepo loginRepo,
            ModelMapper modelMapper,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            TokenRepository tokenRepository,
            JwtService jwtService) {
        this.registrationRepo = registrationRepo;
        this.loginRepo = loginRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.tokenRepository=tokenRepository;
        this.jwtService = jwtService;

    }





    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        CustomerRegistration  customerRegistration;
        if(isValidEmail(identifier)){
            customerRegistration=registrationRepo.findByEmail(identifier).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        }else{
            customerRegistration=registrationRepo.findByPhoneNumber(identifier).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + customerRegistration.getUserType().name()));

        return User.withUsername(customerRegistration.getEmail())
                .password(customerRegistration.getPassword())
                .authorities(authorities)
                .build();
    }

    private boolean isValidEmail  (String input){
        return input != null && input.contains("@");
    }

    @Override
    public String  registerCustomer(CustomerRegistrationDto registrationDto) {
       CustomerRegistration registration=modelMapper.map(registrationDto, CustomerRegistration.class);

       if(registrationRepo.existsByEmail(registration.getEmail())){
           throw new ExceptionHandling("email already exits"+registration.getEmail());
       }

        String encoderPassword= bCryptPasswordEncoder.encode(registration.getPassword());
        registration.setPassword(encoderPassword);

        CustomerRegistration saveCustomer=registrationRepo.save(registration);
        CustomerLoginDto customerLoginDto= modelMapper.map(saveCustomer, CustomerLoginDto.class);
        CustomerLogIn customerLogIn= modelMapper.map(customerLoginDto, CustomerLogIn.class);
        loginRepo.save(customerLogIn);
       // registrationDto =modelMapper.map(saveCustomer, CustomerRegistrationDto.class);

        String jwtToken=jwtService.generateToken(saveCustomer);

        Token token=new Token();
        token.setToken(jwtToken);
        token.setCustomerRegistration(saveCustomer);
        tokenRepository.save(token);

        return jwtToken;
       }







}
