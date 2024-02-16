package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FinanceTenureDto {
    private long finceTenureId;
    private String  assetCategory;
    private String subCategory;
    private String model;
    private String minMonth;
    private String maxMonth;
    private Date RegistrationData;

}
