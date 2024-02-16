package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.CustomerFinanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerFinanceRequestRepo extends JpaRepository<CustomerFinanceRequest, String> {


    List<CustomerFinanceRequest> findByBusinessDetails(BusinessDetails details);

    CustomerFinanceRequest findByBusinessDetailsFinancierIdAndCuUserId(String financierId, String cuUserId);
}

