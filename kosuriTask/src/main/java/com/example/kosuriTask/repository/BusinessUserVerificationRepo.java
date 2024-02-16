package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.BusinessUserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessUserVerificationRepo extends JpaRepository<BusinessUserVerification, Long> {

    List<BusinessUserVerification> findByBusinessDetails(BusinessDetails details);

    BusinessUserVerification findByBusinsVerifctnId(long businsVerifctnId);
}
