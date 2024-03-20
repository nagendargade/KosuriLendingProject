package com.example.kosuriTask.serviceImpl;

import com.example.kosuriTask.entity.AdminLogIn;
import com.example.kosuriTask.entity.CustomerRegistration;
import com.example.kosuriTask.entity.FinancierRegistration;
import com.example.kosuriTask.entity.Registration;
import com.example.kosuriTask.repository.AdminLogInRepo;
import com.example.kosuriTask.repository.CustomerRegistrationRepo;
import com.example.kosuriTask.repository.FinancierRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private CustomerRegistrationRepo customerRegistrationRepo;
    @Autowired
    private FinancierRegistrationRepo financierRegistrationRepo;
   @Autowired
    private AdminLogInRepo adminLogInRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<? extends Registration> userByEmail = findByEmail(username);
        Optional<? extends Registration> userByPhoneNumber = findByPhoneNumber(username);

        if (userByEmail.isPresent()) {
            return buildUserDetails(userByEmail.get());
        } else if (userByPhoneNumber.isPresent()) {
            return buildUserDetails(userByPhoneNumber.get());
        }

        throw new UsernameNotFoundException("User not found with username or phone number: " + username);
    }

    private UserDetails buildUserDetails(Registration registration) {
        return new User(
                registration.getEmail(),
                registration.getPassword(),
                true, true, true, true, // Account non-expired, non-locked, credentials non-expired, enabled
                getAuthorities(registration));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Registration registration) {
        String authority = registration.getUserType().toString();
        return Collections.singleton(new SimpleGrantedAuthority(authority));
    }

    private Optional<? extends Registration> findByEmail(String email) {
        Optional<CustomerRegistration> customerByEmail = customerRegistrationRepo.findByEmail(email);
        if (customerByEmail.isPresent()) {
            return customerByEmail;
        }

        Optional<FinancierRegistration> financierByEmail = financierRegistrationRepo.findByEmail(email);
        if (financierByEmail.isPresent()) {
            return financierByEmail;
        }

        Optional<AdminLogIn> adminByEmail = adminLogInRepo.findByEmail(email);
        if (adminByEmail.isPresent()) {
            return adminByEmail;
        }

        return Optional.empty();
    }

    private Optional<? extends Registration> findByPhoneNumber(String phoneNumber) {
        Optional<CustomerRegistration> customerByPhoneNumber = customerRegistrationRepo.findByPhoneNumber(phoneNumber);
        if (customerByPhoneNumber.isPresent()) {
            return customerByPhoneNumber;
        }

        Optional<FinancierRegistration> financierByPhoneNumber = financierRegistrationRepo.findByPhoneNumber(phoneNumber);
        if (financierByPhoneNumber.isPresent()) {
            return financierByPhoneNumber;
        }

        Optional<AdminLogIn> adminByPhoneNumber = adminLogInRepo.findByPhoneNumber(phoneNumber);
        if (adminByPhoneNumber.isPresent()) {
            return adminByPhoneNumber;
        }

        return Optional.empty();
    }





}
