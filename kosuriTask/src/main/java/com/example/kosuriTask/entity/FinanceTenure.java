package com.example.kosuriTask.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceTenure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long finceTenureId;
    private String  assetCategory;
    private String subCategory;
    private String model;
    private String minMonth;
    private String maxMonth;
    @CreationTimestamp
    private Date registrationData;
    @UpdateTimestamp
    private Date updatedDetails;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    private BusinessDetails businessDetails;

}
