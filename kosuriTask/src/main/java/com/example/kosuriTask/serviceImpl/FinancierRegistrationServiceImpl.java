package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.dto.FinancierLogInDto;
import com.example.kosuriTask.dto.FinancierRegistrationDto;
import com.example.kosuriTask.entity.FinancierLogIn;
import com.example.kosuriTask.entity.FinancierRegistration;
import com.example.kosuriTask.exceptionHandling.ExceptionHandling;
import com.example.kosuriTask.repository.FinancierLogInRepo;
import com.example.kosuriTask.repository.FinancierRegistrationRepo;
import com.example.kosuriTask.security.config.JwtService;
import com.example.kosuriTask.service.FinancierRegistrationService;
import com.example.kosuriTask.token.Token;
import com.example.kosuriTask.token.TokenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FinancierRegistrationServiceImpl implements FinancierRegistrationService {
    private final FinancierRegistrationRepo registrationRepo;
    private final FinancierLogInRepo financierLogInRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Autowired
    public FinancierRegistrationServiceImpl(FinancierRegistrationRepo registrationRepo,
                                            FinancierLogInRepo financierLogInRepo,
                                            ModelMapper modelMapper,
                                            BCryptPasswordEncoder bCryptPasswordEncoder,
                                            TokenRepository tokenRepository,
                                            JwtService jwtService){
        this.registrationRepo=registrationRepo;
        this.financierLogInRepo=financierLogInRepo;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.tokenRepository=tokenRepository;
        this.jwtService=jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
       FinancierRegistration financierRegistration;
       if(isValidEmail(identifier)){
           financierRegistration=registrationRepo.findByEmail(identifier).orElseThrow(
                   ()-> new UsernameNotFoundException("User Not Found"));
       }else{
           financierRegistration=registrationRepo.findByPhoneNumber(identifier).orElseThrow(
                   ()-> new UsernameNotFoundException("User Not Found"));
       }
        Set<GrantedAuthority>  authorities = new HashSet<>();
       authorities.add(new SimpleGrantedAuthority("ROLE_" + financierRegistration.getUserType().name()));
       return User.withUsername(financierRegistration.getEmail())
               .password(financierRegistration.getPassword())
               .authorities(authorities)
               .build();
    }

    private boolean isValidEmail(String input){
        return input !=null && input.contains("@");
    }
    @Override
    public String registerFinancier(FinancierRegistrationDto financierRegistrationDto) {
        FinancierRegistration  registration=modelMapper.map(financierRegistrationDto, FinancierRegistration.class);

        if(registrationRepo.existsByEmail(registration.getEmail())){
            throw new ExceptionHandling("email already exits" + registration.getEmail());
        }

        String encodePassword=bCryptPasswordEncoder.encode(registration.getPassword());
        registration.setPassword(encodePassword);

        FinancierRegistration saveRegistration= registrationRepo.save(registration);
        FinancierLogInDto financierLogInDto=modelMapper.map(saveRegistration, FinancierLogInDto.class);
        FinancierLogIn financierLogIn=modelMapper.map(financierLogInDto, FinancierLogIn.class);
        financierLogInRepo.save(financierLogIn);

     //   financierRegistrationDto=modelMapper.map(saveRegistration, FinancierRegistrationDto.class);

        String jwtToken=jwtService.generateToken(saveRegistration);

        Token  token = new Token();
        token.setToken(jwtToken);
        token.setFinancierRegistration(saveRegistration);
        tokenRepository.save(token);

       // financierRegistrationDto.setJwtToken(jwtToken);

        return jwtToken;

    }



}
