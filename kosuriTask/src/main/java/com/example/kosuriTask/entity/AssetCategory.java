package com.example.kosuriTask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AssetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long astCatgryId;
    private String  assetCategory;
    private String assetSubCategory;
    private String assetModel;
    private String brand;

    @CreationTimestamp
    private Date registrationData;

    @UpdateTimestamp
    private Date updatedDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    private BusinessDetails businessDetails;



}
