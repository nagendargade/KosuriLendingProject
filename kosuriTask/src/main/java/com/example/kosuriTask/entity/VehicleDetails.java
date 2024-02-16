package com.example.kosuriTask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String vehicleId;
    private String brand;
    private String model;
    private String specification;
    private String vehicleCategory;
    private String subCategory;
    private String vehicleType;
    private String status;
    @UpdateTimestamp
    private Date updatedDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    private BusinessDetails businessDetails;

    @PrePersist
    @PreUpdate
    private void prePersistAndUpdate() {
        String sequenceNumber = String.format("%04d", generateSequenceNumber());
        this.vehicleId = brand + model + sequenceNumber;
    }
    private static int sequenceCounter = 1;

    private int generateSequenceNumber() {
        return sequenceCounter++;
    }
}
