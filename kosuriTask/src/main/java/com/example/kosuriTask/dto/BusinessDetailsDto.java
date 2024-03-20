package com.example.kosuriTask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter

public class BusinessDetailsDto  {
    private long userId;
    private String name;
    private String contactEmail;
    private long phoneNumber ;
    private String financierId;
    private String businessName;
    private String nbfcApproved;
    private String nbfcRegistrationNumber;
    private Date registrationDate;
    private String gstRegistration;
    private Date updatedDetails;
    private String location;


}
