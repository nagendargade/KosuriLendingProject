package com.example.kosuriTask.dto;

import com.example.kosuriTask.entity.BusinessDetails;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Setter
@Getter
public class EmiPatternDto {

    private String assetCategory;
    private String subCategory;
    private String model;
    private int minMonth;
    private int maxMonth;
    private double minAmount;
    private double maxAmount;
    private double rateOfInterest;
    private int cutOverPeriod;
    private double percentageIncrease;
    private Date registrationDate;
    private Date updatedDetails;
    private BusinessDetails businessDetails;


}
