package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class FinanceLimitsDto {
    private long finceLimitId;
    private String  assetCategory;
    private String subCategory;
    private String model;
    private String minLending;
    private String maxLending;
    private Date registrationData;
    //privte BusinessDetails bus;

}
