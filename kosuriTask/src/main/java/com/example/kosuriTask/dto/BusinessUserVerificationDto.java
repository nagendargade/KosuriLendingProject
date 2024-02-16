package com.example.kosuriTask.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
public class BusinessUserVerificationDto {
    private long businsVerifctnId;
    private String verification;
    private String officeVerification;
    private String documentVerification;
    private String updatedBy;
    private Date updatedDate;



}
