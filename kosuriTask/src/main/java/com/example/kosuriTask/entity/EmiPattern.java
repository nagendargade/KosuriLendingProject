package com.example.kosuriTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmiPattern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emiId;

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

    @CreationTimestamp
    private Date registrationDate;
    @UpdateTimestamp
    private Date updatedDetails;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    private BusinessDetails businessDetails;



}
