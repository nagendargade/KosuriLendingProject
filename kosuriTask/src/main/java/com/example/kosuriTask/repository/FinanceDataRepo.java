package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.FinanceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinanceDataRepo extends JpaRepository<FinanceData, Long> {

    List<FinanceData> findByBusinessDetails(BusinessDetails details);

    FinanceData findByBusinessDetailsContactEmailAndFincnId(String contactEmail, long fincnId);

    // FinanceData findByBusinessDetailsContactEmailAndFincnId(String contactEmail, long fincnId);
}
