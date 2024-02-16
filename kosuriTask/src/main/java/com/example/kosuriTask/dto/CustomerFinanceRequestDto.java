package com.example.kosuriTask.dto;

import com.example.kosuriTask.entity.BusinessDetails;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CustomerFinanceRequestDto {
    private String cuUserId;
    private LocalDate requestDate;
    private String lendingRequestId;
    private String financeCategory;
    private String assetCategory;
    private String assetType;
    private BusinessDetails businessDetails;
}
