package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.FinancierRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancierRegistrationRepo extends JpaRepository<FinancierRegistration, Long> {
    boolean existsByEmail(String email);

    Optional<FinancierRegistration> findByEmail(String identifier);

    Optional<FinancierRegistration> findByPhoneNumber(String identifier);
}
