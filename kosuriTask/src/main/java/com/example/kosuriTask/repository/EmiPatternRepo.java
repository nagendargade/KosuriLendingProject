package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.EmiPattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmiPatternRepo extends JpaRepository<EmiPattern, Long> {
    List<EmiPattern> findByBusinessDetails(BusinessDetails details);
    EmiPattern findByBusinessDetailsContactEmailAndEmiId(String contactEmail, long emiId);
}
