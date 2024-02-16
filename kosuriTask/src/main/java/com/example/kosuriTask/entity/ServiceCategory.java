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

public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long servcCatrgyId;
    private String serviceCategory;
    private String serviceSubCategory;
    private String serviceName;
    @CreationTimestamp
    private Date registrationData;
    @UpdateTimestamp
    private Date updatedDetails;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    private BusinessDetails businessDetails;
}
