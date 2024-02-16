package com.example.kosuriTask.security.config;

import com.example.kosuriTask.service.CustomerRegistrationService;
import com.example.kosuriTask.service.FinancierRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebConfig {

    private final CustomerRegistrationService registrationService;
    private final FinancierRegistrationService financierRegistrationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DataSource dataSource;

    @Autowired
    public WebConfig(CustomerRegistrationService registrationService,
                     FinancierRegistrationService financierRegistrationService,
                     BCryptPasswordEncoder  bCryptPasswordEncoder,
                     DataSource dataSource){
        this.registrationService=registrationService;
        this.financierRegistrationService=financierRegistrationService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.dataSource=dataSource;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/customerRegistration/**","/api/financierRegistration/**").permitAll()
                .antMatchers("/api/finacirLogin/**","/api/customrLogin/**","/api/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll().and()
                .logout().permitAll();

        http.authenticationProvider(customerDaoAuthenticationProvider());
        http.authenticationProvider(financierDaoAuthenticationProvider());
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider customerDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(registrationService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }
    @Bean
    public DaoAuthenticationProvider financierDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(financierRegistrationService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl userDetailsService = new JdbcDaoImpl();
        userDetailsService.setDataSource(dataSource);
        return userDetailsService;
    }
}
