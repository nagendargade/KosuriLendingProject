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
public class FinanceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fincnId;
    private String financeSegment;
    private String financeSubSegment;
    private String financeCategory;
    @CreationTimestamp
    private Date registrationDate;
    @UpdateTimestamp
    private Date updatedDetails;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    private BusinessDetails businessDetails;


}
