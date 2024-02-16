package com.example.kosuriTask.dto;

import com.example.kosuriTask.entity.BusinessDetails;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class VehicleDetailsDto {
    private long id;
    private String vehicleId;
    private String brand;
    private String model;
    private String specification;
    private String vehicleCategory;
    private String subCategory;
    private String vehicleType;
    private String status;
    private BusinessDetails businessDetails;
}
