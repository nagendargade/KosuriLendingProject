package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.CustomerLogIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerLoginRepo extends JpaRepository<CustomerLogIn, Long> {
    CustomerLogIn findByEmailAndPassword(String email, String password);

    CustomerLogIn findByPhoneNumberAndPassword(String phoneNumber, String password);

    Optional<CustomerLogIn> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<CustomerLogIn> findByEmail(String email);

    Optional<CustomerLogIn> findByPhoneNumber(String phoneNumber);
}
