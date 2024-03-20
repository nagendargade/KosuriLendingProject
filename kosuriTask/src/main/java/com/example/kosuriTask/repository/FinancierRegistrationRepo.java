package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.FinancierRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancierRegistrationRepo extends JpaRepository<FinancierRegistration, Long> {
    boolean existsByEmail(String email);

    Optional<FinancierRegistration> findByEmail(String email);

    Optional<FinancierRegistration> findByPhoneNumber(String phoneNumber);


    Optional<FinancierRegistration> findByEmailOrPhoneNumber(String identifier, String identifier1);
}
