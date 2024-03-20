package com.example.kosuriTask.security.config;

import com.example.kosuriTask.service.CustomerRegistrationService;
import com.example.kosuriTask.service.FinancierRegistrationService;
import com.example.kosuriTask.serviceImpl.CustomUserService;
import com.example.kosuriTask.serviceImpl.CustomerRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebConfig {

    @Autowired
    private CustomUserService registrationService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebConfig(BCryptPasswordEncoder  bCryptPasswordEncoder,
                     JwtAuthenticationFilter jwtAuthenticationFilter){
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/financier/**","/api/customer/**","/api/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().and()
                .logout().permitAll();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(customDaoAuthenticationProvider());
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider customDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(registrationService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }


}
