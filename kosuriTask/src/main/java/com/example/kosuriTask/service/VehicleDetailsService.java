package com.example.kosuriTask.service;

import com.example.kosuriTask.dto.VehicleDetailsDto;
import com.example.kosuriTask.dto.VehicleDetailsDto2;
import com.example.kosuriTask.entity.BusinessDetails;


import java.util.List;

public interface VehicleDetailsService {
    VehicleDetailsDto saveVehicleDetails(VehicleDetailsDto  vehicleDetailsDto,String contactEmail);
    List<VehicleDetailsDto> getVehicleDetails(String contactEmail);
    List<VehicleDetailsDto2> getAllVehicleDetails();
    VehicleDetailsDto updateVehicleDetails(VehicleDetailsDto vehicleDetailsDto, String contactEmail, String vehicleId);




}
