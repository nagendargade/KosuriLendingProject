package com.example.kosuriTask.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFinanceRequest {

    @Id
    private String cuUserId;

    private LocalDate requestDate;

    @Column(unique = true)
    private String lendingRequestId;

    private String financeCategory;
    private String assetCategory;
    private String assetType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="contactEmail",referencedColumnName = "contactEmail")
    @JoinColumn(name="financierId",referencedColumnName = "financierId")
    private BusinessDetails businessDetails;



    @PrePersist
    private void prePersist() {
        // Generate a 4-digit random number
        int random4Digit = generateRandom4DigitNumber();

        this.cuUserId = "cu" + String.format("%04d", cuUserIdSequence.getAndIncrement());

        this.lendingRequestId = "len" + LocalDate.now().getYear() + cuUserId + random4Digit;
    }

    private static AtomicInteger cuUserIdSequence = new AtomicInteger(1);

    private int generateRandom4DigitNumber() {
        return (int) (Math.random() * 9000) + 1000;
    }


}
