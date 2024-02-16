package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class ServiceCategoryDto {
    private long servcCatrgyId;
    private String serviceCategory;
    private String serviceSubCategory;
    private String serviceName;
    private Date RegistrationData;

}
