package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.FinanceLimits;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface FinanceLimitsRepo extends JpaRepository<FinanceLimits, Long> {

    // Optional<FinanceLimits> findByContactEmail(String contactEmail);
   // Optional<FinanceLimits> findByBusinessDetailsContactEmail(String contactEmail);
    List<FinanceLimits> findByBusinessDetails(BusinessDetails businessDetails);

    Optional<FinanceLimits> findByBusinessDetailsContactEmailAndFinceLimitId(String contactEmail,Long finceLimitId);
}
