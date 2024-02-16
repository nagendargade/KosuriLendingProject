package com.example.kosuriTask.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String name;
    @Column(name = "contactEmail", unique = true)
    private String contactEmail;
    private String financierId;
    private long phoneNumber;
    private String businessName;
    private String nbfcApproved;
    private String nbfcRegistrationNumber;
    private String location;

    @CreationTimestamp
    private Date registrationDate;

    private String gstRegistration;

    @UpdateTimestamp
    private Date updatedDetails;


    @OneToMany(mappedBy = "businessDetails", cascade = CascadeType.ALL)
    private List<EmiPattern> emiPattern;




    @PrePersist
    private void prePersist() {
        // Generate the sequence number
        String sequenceNumber = String.format("%04d", generateSequenceNumber());

        this.financierId ="fin"+ sequenceNumber;
    }

    private static int sequenceCounter = 1;

    private  int generateSequenceNumber() {
        return sequenceCounter++;
    }




}
