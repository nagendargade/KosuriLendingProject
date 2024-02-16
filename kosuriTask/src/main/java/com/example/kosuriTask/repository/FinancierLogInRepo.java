package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.FinancierLogIn;
import com.example.kosuriTask.entity.FinancierRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancierLogInRepo extends JpaRepository<FinancierLogIn, Long> {

    Optional<FinancierLogIn> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<FinancierRegistration> findByEmail(String identifier);

   Optional<FinancierRegistration> findByPhoneNumber(String identifier);
}
