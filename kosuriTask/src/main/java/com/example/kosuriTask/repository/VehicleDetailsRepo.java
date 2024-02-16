package com.example.kosuriTask.repository;

import com.example.kosuriTask.entity.BusinessDetails;
import com.example.kosuriTask.entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleDetailsRepo extends JpaRepository<VehicleDetails, String> {

    List<VehicleDetails> findByBusinessDetails(BusinessDetails details);

    VehicleDetails findByBusinessDetailsContactEmailAndVehicleId(String contactEmail, String vehicleId);

   // List<VehicleDetails> findByModelAndBrandAndVehicleTypeAndSubCategory(String model, String brand, String vehicleType, String subCategory);


    List<VehicleDetails> findByBrandAndModelAndVehicleTypeAndSubCategory(String brand, String model, String vehicleType, String subCategory);

    List<VehicleDetails> findByBrandAndModelAndVehicleTypeAndSubCategoryAndBusinessDetailsLocation(String brand, String model, String vehicleType, String subCategory,String location);
}
