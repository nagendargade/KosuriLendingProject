package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.AdminLogIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminLogInRepo extends JpaRepository<AdminLogIn, Long> {

    Optional<AdminLogIn> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<AdminLogIn> findByEmail(String email);
}
