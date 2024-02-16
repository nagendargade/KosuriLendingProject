package com.example.kosuriTask.repository;




import com.example.kosuriTask.entity.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessDetailsRepo extends JpaRepository<BusinessDetails, Long> {

    Optional<BusinessDetails> findByContactEmail(String contactEmail);

    boolean existsByContactEmail(String contactEmail);

    Optional<BusinessDetails> findByFinancierId(String financierId);

    Optional<BusinessDetails> findByContactEmailAndLocation(String contactEmail, String location);
}
