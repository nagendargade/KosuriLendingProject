package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRegistrationRepo extends JpaRepository<CustomerRegistration, Long> {

    boolean existsByEmail(String email);

    Optional<CustomerRegistration> findByEmail(String email);

    Optional<CustomerRegistration> findByPhoneNumber(String identifier);
}
