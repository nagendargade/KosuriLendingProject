package com.example.kosuriTask.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FinanceDataDto {
    private long fincnId;
    private String financeSegment;
    private String financeSubSegment;
    private String financeCategory;
    private Date updatedDetails;


}
