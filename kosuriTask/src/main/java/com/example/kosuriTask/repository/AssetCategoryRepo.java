package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.AssetCategory;
import com.example.kosuriTask.entity.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AssetCategoryRepo extends JpaRepository<AssetCategory, Long> {

    List<AssetCategory> findByBusinessDetails(BusinessDetails details);

    AssetCategory findByBusinessDetailsContactEmailAndAstCatgryId(String contactEmail, Long astCatgryId);
}
