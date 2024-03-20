package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.AdminLogIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminLogInRepo extends JpaRepository<AdminLogIn, Long> {

    Optional<AdminLogIn> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<AdminLogIn> findByEmail(String email);

    Optional<AdminLogIn> findByPhoneNumber(String phoneNumber);
}
