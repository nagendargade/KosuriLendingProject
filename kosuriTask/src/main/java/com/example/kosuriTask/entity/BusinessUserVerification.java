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
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long businsVerifctnId;
    private String verification;
    private String officeVerification;
    private String documentVerification;
    private String updatedBy;
    @UpdateTimestamp
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name ="financierId",referencedColumnName = "financierId")
    private BusinessDetails businessDetails;


}
