package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ServiceCategoryRepo extends JpaRepository<ServiceCategory, Long> {
    

    ServiceCategory findByBusinessDetailsContactEmailAndServcCatrgyId(String contactEmail, Long servcCatrgyId);

    List<ServiceCategory> findByBusinessDetails(BusinessDetails details);
}
